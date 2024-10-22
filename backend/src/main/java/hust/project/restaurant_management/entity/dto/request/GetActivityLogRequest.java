package hust.project.restaurant_management.entity.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetActivityLogRequest {
    private Long page;
    private Long pageSize;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
}
