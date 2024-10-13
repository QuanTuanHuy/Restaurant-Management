package hust.project.restaurant_management.entity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateSupplierRequest {
    private String name;

    private String code;

    private String address;

    private String email;

    private String phoneNumber;
}
