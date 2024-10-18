package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.entity.ShiftEntity;
import hust.project.restaurant_management.entity.UserEntity;
import hust.project.restaurant_management.entity.WorkScheduleEntity;
import hust.project.restaurant_management.entity.dto.request.CreateWorkScheduleRequest;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.mapper.IWorkScheduleMapper;
import hust.project.restaurant_management.port.IShiftPort;
import hust.project.restaurant_management.port.IUserPort;
import hust.project.restaurant_management.port.IWorkSchedulePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateWorkScheduleUseCase {
    private final IWorkSchedulePort workSchedulePort;
    private final IWorkScheduleMapper workScheduleMapper;
    private final IUserPort userPort;
    private final IShiftPort shiftPort;

    private final CreateWorkAttendanceUseCase createWorkAttendanceUseCase;

    @Transactional
    public WorkScheduleEntity createWorkSchedule(CreateWorkScheduleRequest request) {
        if (request.getDate().isBefore(LocalDate.now())) {
            log.error("[CreateWorkScheduleUseCase] create work schedule failed, date is invalid");
            throw new AppException(ErrorCode.CREATE_WORK_SCHEDULE_FAILED);
        }

        UserEntity user = userPort.getUserById(request.getUserId());

        ShiftEntity shift = shiftPort.getShiftById(request.getShiftId());

        WorkScheduleEntity workSchedule = workScheduleMapper.toEntityFromRequest(request);

        WorkScheduleEntity savedWorkSchedule = workSchedulePort.save(workSchedule);


        createWorkAttendanceUseCase.createWorkAttendance(savedWorkSchedule);


        savedWorkSchedule.setUser(user);
        savedWorkSchedule.setShift(shift);

        return savedWorkSchedule;
    }
}
