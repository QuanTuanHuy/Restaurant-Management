package hust.project.restaurant_management.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Builder
public class UserModel extends AuditTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "gender")
    private String gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "cccd")
    private String cccd;

    @Column(name = "cv_img")
    private String cvImg;

    @Column(name = "position")
    private String position;

    @Column(name = "salary_type")
    private String salaryType;

    @Column(name = "salary_per_hour")
    private Double salaryPerHour;

    @Column(name = "salary_per_month")
    private Double salaryPerMonth;
}
