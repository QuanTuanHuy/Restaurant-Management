package hust.project.restaurant_management.entity.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentSalaryDetailRequest {
    @NotNull
    @Min(value = 1, message = "Salary detail id must be greater than 0")
    private Long salaryDetailId;

    @NotNull
    private Double paidSalary;
}
