package hust.project.restaurant_management.entity.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateSalaryPeriodRequest {
    private LocalDate fromDate;
    private LocalDate toDate;
    private String title;
}
