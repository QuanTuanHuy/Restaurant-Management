package hust.project.restaurant_management.entity.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequest {
    private String email;

    private String password;

    private String name;

    private String phoneNumber;

    private String gender;

    private LocalDate dateOfBirth;

    private Long roleId;

    private String cccd;

    private String cvImg;

    private String position;

    private String salaryType;

    private Double salaryPerHour;

    private Double salaryPerMonth;
}
