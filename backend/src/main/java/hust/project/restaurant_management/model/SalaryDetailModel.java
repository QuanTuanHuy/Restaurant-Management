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

    private String code;

    private Long userId;

    private Long salaryPeriodId;

    private Long totalWorkingDays;

    private Long totalWorkingHours;

    private Double totalSalary;

    private Double paidSalary;

    private String paymentMethod;
}
