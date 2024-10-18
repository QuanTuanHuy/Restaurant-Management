package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.constants.WorkAttendanceStatusEnum;
import hust.project.restaurant_management.entity.WorkAttendanceEntity;
import hust.project.restaurant_management.entity.dto.request.UpdateWorkAttendanceRequest;
import hust.project.restaurant_management.port.IWorkAttendancePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateWorkAttendanceUseCase {

    private final IWorkAttendancePort workAttendancePort;
    private final GetWorkAttendanceUseCase getWorkAttendanceUseCase;

    public WorkAttendanceEntity updateWorkAttendance(Long id, UpdateWorkAttendanceRequest request) {
        var workAttendance = getWorkAttendanceUseCase.getDetailWorkAttendance(id);

        workAttendance.setNote(request.getNote());
        workAttendance.setCheckInTime(request.getCheckInTime());
        workAttendance.setCheckOutTime(request.getCheckOutTime());

        if (request.getStatus().equals(WorkAttendanceStatusEnum.ABSENT.name())) {
            workAttendance.setStatus(WorkAttendanceStatusEnum.ABSENT.name());
            return workAttendancePort.save(workAttendance);
        }

        var shift = workAttendance.getWorkSchedule().getShift();
        if (request.getCheckInTime() != null && request.getCheckOutTime() != null) {

            if (!request.getCheckInTime().isAfter(shift.getStartTime()) && !request.getCheckOutTime().isBefore(shift.getEndTime())) {
                workAttendance.setStatus(WorkAttendanceStatusEnum.ON_TIME.name());
            } else {
                workAttendance.setStatus(WorkAttendanceStatusEnum.LATE_OR_LEAVE_EARLY.name());
            }
        } else if (request.getCheckInTime() != null) {
            workAttendance.setStatus(WorkAttendanceStatusEnum.NOT_YET_CLOCKED_IN.name());
        } else if (request.getCheckOutTime() != null) {
            workAttendance.setStatus(WorkAttendanceStatusEnum.NOT_YET_CLOCKED_IN.name());
        }

        return workAttendancePort.save(workAttendance);

    }
}
