package hust.project.restaurant_management.entity.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateStockHistoryRequest {
    private Long supplierId;
    private Long userId;
    private String code;
    private String status;
    private String note;

    private List<CreateStockHistoryItemRequest> stockHistoryItems;
}
