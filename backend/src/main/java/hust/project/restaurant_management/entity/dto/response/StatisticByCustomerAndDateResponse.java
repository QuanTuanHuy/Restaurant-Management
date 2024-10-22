package hust.project.restaurant_management.entity.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatisticByCustomerAndDateResponse {
    private Long totalCustomer;
    private List<CustomerStatisticPerDateResponse> customerStatistics;
}
