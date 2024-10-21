package hust.project.restaurant_management.entity.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetStatisticByRevenueRequest {
    private LocalDate startDate;
    private LocalDate endDate;
}
