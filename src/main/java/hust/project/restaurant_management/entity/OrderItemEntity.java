package hust.project.restaurant_management.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemEntity {
    private Long id;

    private Long orderId;

    private Long menuItemId;

    private Long orderedQuantity;

    private Long reservedQuantity;

    private Double price;

    private String note;

    private String status;

    private MenuItemEntity menuItem;
}
