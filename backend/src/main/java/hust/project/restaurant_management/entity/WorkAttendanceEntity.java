package hust.project.restaurant_management.entity;

import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkAttendanceEntity {
    private Long id;

    private Long workScheduleId;

    private LocalTime checkInTime;

    private LocalTime checkOutTime;

    private String status;

    private String note;

    private WorkScheduleEntity workSchedule;
}
