package hust.project.restaurant_management.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "work_schedules", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"userId", "shiftId", "date"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkScheduleModel extends AuditTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "shiftId")
    private Long shiftId;

    @Column(name = "date")
    private LocalDate date;
}
