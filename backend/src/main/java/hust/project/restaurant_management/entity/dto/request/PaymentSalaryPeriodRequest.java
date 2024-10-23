package hust.project.restaurant_management.entity.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaymentSalaryPeriodRequest {
    @NotBlank
    private String paymentMethod;
    private String note;

    @NotBlank
    private List<PaymentSalaryDetailRequest> paymentSalaryDetails;
}
