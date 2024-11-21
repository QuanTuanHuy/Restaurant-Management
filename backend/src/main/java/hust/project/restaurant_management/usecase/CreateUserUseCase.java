package hust.project.restaurant_management.usecase;


import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.constants.GenderEnum;
import hust.project.restaurant_management.entity.RoleEntity;
import hust.project.restaurant_management.entity.UserEntity;
import hust.project.restaurant_management.entity.dto.request.CreateUserRequest;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.mapper.IUserMapper;
import hust.project.restaurant_management.port.IRolePort;
import hust.project.restaurant_management.port.IUserPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateUserUseCase {
    private final IUserPort userPort;
    private final IRolePort rolePort;
    private final IUserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserEntity createUser(CreateUserRequest request) {
        UserEntity existedUser = userPort.getUserByEmail(request.getEmail());
        if (existedUser != null) {
            log.error("[CreateUserUseCase] email existed: {}", request.getEmail());
            throw new AppException(ErrorCode.USER_EMAIL_EXISTED);
        }

        UserEntity user = userMapper.toEntityFromRequest(request);
        user.setGender(GenderEnum.valueOf(request.getGender()).name());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user = userPort.save(user);
        RoleEntity role = rolePort.getRoleById(user.getRoleId());
        user.setRole(role);

        return user;
    }
}
