package hust.project.restaurant_management.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalaryDetailEntity {
    private Long id;

    private String code;

    private Long userId;

    private Long salaryPeriodId;

    private Long totalWorkingDays;

    private Long totalWorkingHours;

    private Double totalSalary;

    private Double paidSalary;

    private String paymentMethod;

    private UserEntity user;
}
