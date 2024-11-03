package hust.project.restaurant_management.entity.dto.request;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetOrderRequest {
    private Long page;
    private Long pageSize;
    private List<String> orderStatus;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String paymentMethod;
    private List<Long> tableIds;

    private String userName;
    private String customerName;
    private String note;
}
