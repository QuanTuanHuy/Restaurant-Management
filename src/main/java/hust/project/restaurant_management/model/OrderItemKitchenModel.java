package hust.project.restaurant_management.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_item_kitchens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemKitchenModel extends AuditTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "menu_item_id")
    private Long menuItemId;

    @Column(name = "table_id")
    private Long tableId;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "status")
    private String status;

    @Column(name = "note")
    private String note;

    @Column(name = "received_time")
    private LocalDateTime receivedTime;
}
