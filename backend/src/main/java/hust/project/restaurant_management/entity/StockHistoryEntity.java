package hust.project.restaurant_management.entity;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockHistoryEntity {
    private Long id;

    private String code;

    private Long supplierId;

    private Long userId;

    private String status;

    private String note;

    private Double totalPrice;

    private LocalDateTime dateTime;

    private List<StockHistoryItemEntity> stockHistoryItems;

    private UserEntity user;

    private SupplierEntity supplier;
}
