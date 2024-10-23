package hust.project.restaurant_management.entity.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateSalaryPeriodStatusRequest {
    @NotBlank
    private String status;
}
