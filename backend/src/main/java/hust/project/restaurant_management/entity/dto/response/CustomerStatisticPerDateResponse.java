package hust.project.restaurant_management.entity.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerStatisticPerDateResponse {
    private LocalDate date;
    private Long count;
}
