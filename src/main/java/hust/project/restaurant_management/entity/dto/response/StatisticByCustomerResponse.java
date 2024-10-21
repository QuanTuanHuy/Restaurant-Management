package hust.project.restaurant_management.entity.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatisticByCustomerResponse {
    private Long totalCustomer;
    private List<CustomerStatisticPerDateResponse> customerStatistics;
}
