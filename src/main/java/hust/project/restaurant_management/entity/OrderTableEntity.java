package hust.project.restaurant_management.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderTableEntity {
    private Long id;

    private Long orderId;

    private Long tableId;

    private String status;

    private TableEntity table;
}
