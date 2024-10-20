package hust.project.restaurant_management.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemKitchenEntity {
    private Long id;

    private Long orderId;

    private Long tableId;

    private Long menuItemId;

    private Long quantity;

    private String status;

    private String note;

    private LocalDateTime receivedTime;


    private MenuItemEntity menuItem;

    private TableEntity table;
}
