package hust.project.restaurant_management.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "stock_histories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockHistoryModel extends AuditTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "supplier_id")
    private Long supplierId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "status")
    private String status;

    @Column(name = "note")
    private String note;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "date_time")
    private LocalDateTime dateTime;
}
