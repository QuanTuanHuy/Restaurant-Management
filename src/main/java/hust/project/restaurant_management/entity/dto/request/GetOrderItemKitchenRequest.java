package hust.project.restaurant_management.entity.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetOrderItemKitchenRequest {
    @NotBlank
    private String status;
    private Long orderId;
    private Long tableId;
}
