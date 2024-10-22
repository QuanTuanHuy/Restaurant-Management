package hust.project.restaurant_management.entity.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePaymentRequest {
    private Long orderId;
    private Double totalPrice;
    private Double promotion;
    private Double needToPay;
    private String paymentMethod;
}
