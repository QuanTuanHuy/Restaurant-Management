package hust.project.restaurant_management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    private Long id;

    private String email;

    @JsonIgnore
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

    private Boolean isActive;

    private RoleEntity role;

}
