package hust.project.restaurant_management.entity.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UpdateProductRequest {
    private String name;

    private String description;

    private String code;

    private Double sellingPrice;

    private String thumbnailImg;

    private String status;

    private String productType;

    private Set<String> images;
}
