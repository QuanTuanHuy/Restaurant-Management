package hust.project.restaurant_management.entity.dto.request;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetStockHistoryRequest {
    private Long page;
    private Long pageSize;

    private String code;
    private String supplierName;
    private String userName;
    private String productName;
    private String note;

    private LocalDateTime fromDate;
    private LocalDateTime toDate;

    private List<String> statuses;

}
