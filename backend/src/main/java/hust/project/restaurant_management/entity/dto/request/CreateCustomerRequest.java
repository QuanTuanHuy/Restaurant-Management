package hust.project.restaurant_management.entity.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCustomerRequest {
    private String name;

    private String phoneNumber;

    private String address;

    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dob;

    private String gender;
}
