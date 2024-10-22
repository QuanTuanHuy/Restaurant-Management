package hust.project.restaurant_management.entity.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetStockHistoryRequest {
    private Long page;
    private Long pageSize;
//    private Long productId;
//    private String supplierName;
//    private String productName;
    private Long supplierId;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
}
