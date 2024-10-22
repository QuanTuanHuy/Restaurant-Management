package hust.project.restaurant_management.entity.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetOrderTableRequest {
    private Long page;
    private Long pageSize;
    private Long orderId;
    private Long tableId;
}
