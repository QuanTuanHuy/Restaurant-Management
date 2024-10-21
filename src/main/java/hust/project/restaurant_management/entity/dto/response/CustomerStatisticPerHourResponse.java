package hust.project.restaurant_management.entity.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerStatisticPerHourResponse {
    private Integer hour;
    private Integer count;
}
