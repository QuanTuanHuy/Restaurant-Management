package hust.project.restaurant_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "stock_history_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockHistoryItemModel extends AuditTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long stockHistoryId;

    private Long productId;

    private Long quantity;

    private Double pricePerUnit;
}
