package hust.project.restaurant_management.entity.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetCustomerRequest {
    private Long page;
    private Long pageSize;
    private String name;
    private String phoneNumber;
    private String address;
    private String gender;
    private Double beginTotalCost;
    private Double endTotalCost;
    private LocalDate beginDateOfBirth;
    private LocalDate endDateOfBirth;
}
