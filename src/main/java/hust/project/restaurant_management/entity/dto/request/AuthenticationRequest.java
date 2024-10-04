package hust.project.restaurant_management.entity.dto.request;

import lombok.Getter;

@Getter
public class AuthenticationRequest {
    String email;
    String password;
}
