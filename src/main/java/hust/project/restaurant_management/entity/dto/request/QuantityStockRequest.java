package hust.project.restaurant_management.entity.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuantityStockRequest {
    List<QuantityStockItemRequest> items;
}
