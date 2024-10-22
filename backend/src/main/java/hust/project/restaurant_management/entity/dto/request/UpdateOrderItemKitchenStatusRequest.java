package hust.project.restaurant_management.entity.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateOrderItemKitchenStatusRequest {
    @NotEmpty
    private List<Long> orderItemKitchenIds;

    @NotBlank
    private String status;
}
