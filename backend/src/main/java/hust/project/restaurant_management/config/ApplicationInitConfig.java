package hust.project.restaurant_management.config;

import hust.project.restaurant_management.entity.RoleEntity;
import hust.project.restaurant_management.entity.UserEntity;
import hust.project.restaurant_management.port.IRolePort;
import hust.project.restaurant_management.port.IUserPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationInitConfig {
    private final PasswordEncoder passwordEncoder;

    static final String ADMIN_EMAIL = "admin@gmail.com";
    static final String ADMIN_PASSWORD = "123456";

    @Bean
    ApplicationRunner init(IUserPort userPort, IRolePort rolePort) {
        return args -> {
            if (userPort.getUserByEmail(ADMIN_EMAIL) == null) {
                log.info("[ApplicationInitConfig] Create admin user");

                var adminRole = rolePort.save(RoleEntity.builder()
                                .name("ADMIN")
                                .description("Admin role")
                        .build());

                userPort.save(UserEntity.builder()
                                .email(ADMIN_EMAIL)
                                .password(passwordEncoder.encode(ADMIN_PASSWORD))
                                .name("Admin")
                                .roleId(adminRole.getId())
                        .build());
            }
        };
    }
}
