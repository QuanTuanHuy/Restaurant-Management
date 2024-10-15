package hust.project.restaurant_management.entity.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStockHistoryItemRequest {
    private Long productId;

    private Long quantity;

    private Double pricePerUnit;
}
