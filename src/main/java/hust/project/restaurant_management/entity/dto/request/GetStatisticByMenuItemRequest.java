package hust.project.restaurant_management.entity.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetStatisticByMenuItemRequest {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String category;
    private Integer limit;
}
