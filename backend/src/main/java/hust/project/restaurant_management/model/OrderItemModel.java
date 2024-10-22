package hust.project.restaurant_management.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "menu_item_id")
    private Long menuItemId;

    @Column(name = "ordered_quantity")
    private Long orderedQuantity;

    @Column(name = "reserved_quantity")
    private Long reservedQuantity;

    @Column(name = "price")
    private Double price;

    @Column(name = "note")
    private String note;

    private String status;
}
