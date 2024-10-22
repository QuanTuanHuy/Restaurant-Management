package hust.project.restaurant_management.entity;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalaryPeriodEntity {
    private Long id;

    private String code;

    private String title;

    private LocalDate fromDate;

    private LocalDate toDate;

    private Double totalSalary;

    private Double paidSalary;

    private String status;

    private List<SalaryDetailEntity> salaryDetails;
}
