package hust.project.restaurant_management.entity.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class CreateProductRequest {
    private String name;

    private String description;

    private String code;

    private Double costPrice;

    private Double sellingPrice;

    private String thumbnailImg;

    private String status;

    private String productType;

    private List<String> images;
}
