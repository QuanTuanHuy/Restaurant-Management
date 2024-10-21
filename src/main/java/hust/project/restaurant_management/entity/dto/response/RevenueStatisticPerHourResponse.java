package hust.project.restaurant_management.entity.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RevenueStatisticPerHourResponse {
    private Integer hour;
    private Double revenue;
}
