package hust.project.restaurant_management.entity.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuItemQuantityRequest {
    @NotNull
    @Min(value = 1, message = "Menu item id must be greater than 0")
    private Long menuItemId;

    @NotNull
    @Min(value = 1, message = "Quantity must be greater than 0")
    private Long quantity;

    private String note;
}
