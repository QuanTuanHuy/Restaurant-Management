package hust.project.restaurant_management.entity.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetOrderItemKitchenRequest {
    private String status;
    private Long orderId;
    private Long tableId;
}
