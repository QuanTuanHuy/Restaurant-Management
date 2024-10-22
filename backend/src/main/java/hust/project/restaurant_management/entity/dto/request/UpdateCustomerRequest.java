package hust.project.restaurant_management.entity.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCustomerRequest {
    private String name;

    private String phoneNumber;

    private String address;

    private String email;

    private LocalDate dob;

    private String gender;
}
