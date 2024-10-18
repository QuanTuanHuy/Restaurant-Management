package hust.project.restaurant_management.entity.dto.request;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetWorkScheduleRequest {
    private List<Long> userIds;

    private LocalDate startDate;

    private LocalDate endDate;
}
