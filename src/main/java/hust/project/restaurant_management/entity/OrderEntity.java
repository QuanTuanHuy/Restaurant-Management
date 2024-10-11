package hust.project.restaurant_management.entity;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEntity {
    private Long id;

    private Long customerId;

    private Long userId;

    private String orderStatus;

    private Double totalCost;

    private Long numberOfPeople;

    private String note;

    private LocalDateTime checkInTime;

    private LocalDateTime checkOutTime;

    private Long paymentId;

    private String paymentStatus;

    private String paymentMethod;

    private CustomerEntity customer;

    private List<OrderTableEntity> orderTables;

    private List<OrderItemEntity> orderItems;
}
