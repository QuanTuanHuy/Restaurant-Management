package hust.project.restaurant_management.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentEntity {
    private Long id;
    private Double totalPrice;
    private Double promotion;
    private Double needToPay;
    private String paymentMethod;
}
