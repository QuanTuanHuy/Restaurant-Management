package hust.project.restaurant_management.service.impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.entity.UserEntity;
import hust.project.restaurant_management.entity.dto.request.AuthenticationRequest;
import hust.project.restaurant_management.entity.dto.request.IntrospectRequest;
import hust.project.restaurant_management.entity.dto.response.AuthenticationResponse;
import hust.project.restaurant_management.entity.dto.response.IntrospectResponse;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.port.IRolePort;
import hust.project.restaurant_management.port.IUserPort;
import hust.project.restaurant_management.service.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService implements IAuthenticationService {

    @Value("${app.jwt.valid-duration}")
    @NonFinal
    private Long VALID_DURATION;

    @Value("${app.jwt.signer-key}")
    private String SIGNER_KEY;

    private final IUserPort userPort;
    private final IRolePort rolePort;

    @Override
    public IntrospectResponse introspect(IntrospectRequest request) {
        var token = request.getToken();
        boolean isValid = true;

        try {
            verifyToken(token);
        } catch (ParseException | JOSEException e) {
            isValid = false;
        }

        return IntrospectResponse.builder()
                .valid(isValid)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        log.info("Authenticating user with email: {}", request.getEmail());
        UserEntity existedUser = userPort.getUserByEmail(request.getEmail());

        if (existedUser == null) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(request.getPassword(), existedUser.getPassword())) {
            log.error("Password not match");
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        String token = generateToken(existedUser);

        return AuthenticationResponse.builder()
                .token(token).valid(true)
                .build();
    }

    private String generateToken(UserEntity user) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getId().toString())
                .issuer("quantuanhuy")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(VALID_DURATION, ChronoUnit.SECONDS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(user))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Error when sign JWT", e);
            throw new AppException(ErrorCode.CREATE_TOKEN_FAILED);
        }
    }

    private SignedJWT verifyToken(String token) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        if (!verified || expirationTime.before(new Date())) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        return signedJWT;

    }

    private String buildScope(UserEntity user) {
        return rolePort.getRoleById(user.getRoleId()).getName();
    }
}
