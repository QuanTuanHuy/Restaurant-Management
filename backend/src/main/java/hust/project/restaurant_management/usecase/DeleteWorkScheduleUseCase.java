package hust.project.restaurant_management.usecase;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.entity.WorkScheduleEntity;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.port.IWorkAttendancePort;
import hust.project.restaurant_management.port.IWorkSchedulePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteWorkScheduleUseCase {
    private final IWorkSchedulePort workSchedulePort;
    private final IWorkAttendancePort workAttendancePort;

    @Transactional
    public void deleteWorkSchedule(Long id) {
        WorkScheduleEntity workSchedule = workSchedulePort.getWorkScheduleById(id);

        if (workSchedule.getDate().isBefore(LocalDate.now())) {
            log.error("[DeleteWorkScheduleUseCase] delete work schedule failed: work schedule is in the past");
            throw new AppException(ErrorCode.DELETE_WORK_SCHEDULE_FAILED);
        }

        workAttendancePort.deleteWorkAttendanceByWorkScheduleId(id);
        workSchedulePort.deleteWorkScheduleById(id);

    }
}
