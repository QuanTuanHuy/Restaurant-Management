package hust.project.restaurant_management.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "salary_periods")
@Builder
public class SalaryPeriodModel extends AuditTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String title;

    private LocalDate fromDate;

    private LocalDate toDate;

    private Double totalSalary;

    private Double paidSalary;

    private String status;
}
