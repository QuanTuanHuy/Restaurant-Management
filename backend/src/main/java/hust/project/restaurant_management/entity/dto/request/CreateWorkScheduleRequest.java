package hust.project.restaurant_management.entity.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateWorkScheduleRequest {
    @NotNull
    private Long userId;

    @NotNull
    private Long shiftId;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
}
