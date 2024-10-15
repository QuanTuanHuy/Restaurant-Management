package hust.project.restaurant_management.entity.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetProductRequest {
    private Long page;
    private Long pageSize;
    private String name;
    private String productType;
    private String status;
    private Double priceFrom;
    private Double priceTo;
    private String sortBy;
    private String sortType;
}
