package hust.project.restaurant_management.entity.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItemStatisticResponse {
    private Long menuItemId;
    private String menuItemName;
    private Long quantity;
    private Double revenue;
}
