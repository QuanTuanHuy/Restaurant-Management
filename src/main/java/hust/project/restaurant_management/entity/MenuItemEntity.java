package hust.project.restaurant_management.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuItemEntity {
    private Long id;

    private String title;

    private String description;

    private Double costPrice;

    private Double sellingPrice;

    private String thumbnailImg;

    private Long menuSectionId;

    private MenuSectionEntity menuSection;
}
