package hust.project.restaurant_management.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity {
    private Long id;

    private String name;

    private String description;

    private String code;

    private Double costPrice;

    private Double sellingPrice;

    private String thumbnailImg;

    private String status;

    private String productType;

    private StockEntity stock;

    private List<ImageEntity> images;
}
