package hust.project.restaurant_management.entity;

import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShiftEntity {
    private Long id;

    private String name;

    private LocalTime startTime;

    private LocalTime endTime;

    private String status;
}
