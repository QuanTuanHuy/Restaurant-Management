package hust.project.restaurant_management.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplierEntity {
    private Long id;

    private String name;

    private String code;

    private String address;

    private String email;

    private String phoneNumber;

    private Double totalDebt;

    private Double totalCost;

    private String status;
}
