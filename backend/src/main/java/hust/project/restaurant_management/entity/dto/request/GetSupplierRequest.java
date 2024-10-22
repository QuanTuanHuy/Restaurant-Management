package hust.project.restaurant_management.entity.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetSupplierRequest {
    private Long page;
    private Long pageSize;
    private String name;
    private String phoneNumber;
    private Double debtFrom;
    private Double debtTo;
    private Double totalCostFrom;
    private Double totalCostTo;
    private String status;
}
