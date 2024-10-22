package hust.project.restaurant_management.controller;

import hust.project.restaurant_management.entity.dto.request.AuthenticationRequest;
import hust.project.restaurant_management.entity.dto.request.IntrospectRequest;
import hust.project.restaurant_management.entity.dto.response.Resource;
import hust.project.restaurant_management.service.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {
    private final IAuthenticationService authenticationService;

    @PostMapping("/introspect")
    public ResponseEntity<Resource> introspect(@RequestBody IntrospectRequest request) {
        return ResponseEntity.ok(new Resource(authenticationService.introspect(request)));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Resource> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(new Resource(authenticationService.authenticate(request)));
    }

}
