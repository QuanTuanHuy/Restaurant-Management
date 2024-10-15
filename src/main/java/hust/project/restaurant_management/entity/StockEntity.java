package hust.project.restaurant_management.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockEntity {
    private Long id;

    private Long productId;

    private Long availableQuantity;

    private Long soldQuantity;
}
