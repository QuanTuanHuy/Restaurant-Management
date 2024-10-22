package hust.project.restaurant_management.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockHistoryItemEntity {
    private Long id;

    private Long stockHistoryId;

    private Long productId;

    private Long quantity;

    private Double pricePerUnit;

    private ProductEntity product;
}
