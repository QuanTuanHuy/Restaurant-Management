package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.constants.WorkAttendanceStatusEnum;
import hust.project.restaurant_management.entity.WorkAttendanceEntity;
import hust.project.restaurant_management.entity.WorkScheduleEntity;
import hust.project.restaurant_management.port.IWorkAttendancePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateWorkAttendanceUseCase {

    private final IWorkAttendancePort workAttendancePort;

    @Transactional
    void createWorkAttendance(WorkScheduleEntity workSchedule) {
        var workAttendance = WorkAttendanceEntity.builder()
                .workScheduleId(workSchedule.getId())
                .status(WorkAttendanceStatusEnum.NOT_STARTED_YET.name())
                .build();

        workAttendancePort.save(workAttendance);
    }
}
