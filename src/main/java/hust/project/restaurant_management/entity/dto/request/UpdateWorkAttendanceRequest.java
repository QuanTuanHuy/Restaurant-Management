package hust.project.restaurant_management.entity.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class UpdateWorkAttendanceRequest {
    private LocalTime checkInTime;

    private LocalTime checkOutTime;

    @NotBlank
    private String status;

    private String note;
}
