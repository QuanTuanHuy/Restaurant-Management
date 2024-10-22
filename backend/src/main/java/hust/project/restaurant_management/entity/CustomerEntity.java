package hust.project.restaurant_management.entity;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerEntity {
    private Long id;

    private String name;

    private String phoneNumber;

    private String address;

    private String email;

    private LocalDate dob;

    private String gender;

    private Double totalCost;
}
