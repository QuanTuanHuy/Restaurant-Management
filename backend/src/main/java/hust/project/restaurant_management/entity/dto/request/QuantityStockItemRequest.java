package hust.project.restaurant_management.entity.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuantityStockItemRequest {
    private Long stockId;
    private Long quantity;
}
