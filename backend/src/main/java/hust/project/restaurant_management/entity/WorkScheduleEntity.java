package hust.project.restaurant_management.entity;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkScheduleEntity {
    private Long id;

    private Long userId;

    private Long shiftId;

    private LocalDate date;

    private UserEntity user;

    private ShiftEntity shift;
}
