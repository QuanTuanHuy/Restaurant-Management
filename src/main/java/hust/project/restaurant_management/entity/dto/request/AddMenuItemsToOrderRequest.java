package hust.project.restaurant_management.entity.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddMenuItemsToOrderRequest {
    private List<MenuItemQuantityRequest> menuItemsQuantity;
}
