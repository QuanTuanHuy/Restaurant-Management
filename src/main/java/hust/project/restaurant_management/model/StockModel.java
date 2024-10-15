package hust.project.restaurant_management.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stocks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockModel extends AuditTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "available_quantity")
    private Long availableQuantity;

    @Column(name = "sold_quantity")
    private Long soldQuantity;
}
