package hust.project.restaurant_management.entity.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddMenuItemsToOrderRequest {
    @NotBlank
    private List<MenuItemQuantityRequest> menuItemsQuantity;
}
