package hust.project.restaurant_management.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuSectionEntity {
    private Long id;

    private String title;

    private String description;

    private List<MenuItemEntity> menuItems;
}
