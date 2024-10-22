package hust.project.restaurant_management.entity.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetSalaryDetailRequest {
    private Long page;
    private Long pageSize;
    private Long userId;
    private Long salaryPeriodId;
}
