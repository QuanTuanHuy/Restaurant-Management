package hust.project.restaurant_management.entity.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentSalaryDetailRequest {
    @NotNull
    private Long salaryDetailId;

    @NotNull
    private Double paidSalary;
}
