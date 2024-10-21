package hust.project.restaurant_management.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderModel extends AuditTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "total_cost")
    private Double totalCost;

    @Column(name = "number_of_people")
    private Long numberOfPeople;

    @Column(name = "note")
    private String note;

    @Column(name = "check_in_time")
    private LocalDateTime checkInTime;

    @Column(name = "check_out_time")
    private LocalDateTime checkOutTime;

    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "payment_method")
    private String paymentMethod;
}
