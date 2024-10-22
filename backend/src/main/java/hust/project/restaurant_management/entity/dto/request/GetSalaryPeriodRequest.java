package hust.project.restaurant_management.entity.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetSalaryPeriodRequest {
    private Long page;
    private Long pageSize;
    private String title;
    private String status;
}
