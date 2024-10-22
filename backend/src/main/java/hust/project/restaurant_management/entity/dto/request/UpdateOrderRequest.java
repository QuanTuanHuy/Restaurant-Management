package hust.project.restaurant_management.entity.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class UpdateOrderRequest {
    private Long userId;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    List<Long> tableIds;
    private Long numberOfPeople;
    private String note;
}
