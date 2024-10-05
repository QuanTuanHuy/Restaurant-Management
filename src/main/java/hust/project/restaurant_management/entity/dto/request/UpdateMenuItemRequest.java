package hust.project.restaurant_management.entity.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateMenuItemRequest {
    private String title;

    private String description;

    private Double costPrice;

    private Double sellingPrice;

    private String thumbnailImg;

    private Long menuSectionId;
}
