package hust.project.restaurant_management.entity.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuItemQuantityRequest {
    private Long menuItemId;
    private Long quantity;
    private String note;
}
