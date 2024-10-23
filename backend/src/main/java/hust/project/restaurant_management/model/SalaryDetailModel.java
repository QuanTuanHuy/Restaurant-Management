package hust.project.restaurant_management.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "salary_details")
@Builder
public class SalaryDetailModel extends AuditTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "salary_period_id")
    private Long salaryPeriodId;

    @Column(name = "total_working_days")
    private Long totalWorkingDays;

    @Column(name = "total_working_hours")
    private Long totalWorkingHours;

    @Column(name = "total_salary")
    private Double totalSalary;

    @Column(name = "paid_salary")
    private Double paidSalary;

    @Column(name = "status")
    private String status;

    @Column(name = "payment_method")
    private String paymentMethod;
}
