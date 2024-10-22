package hust.project.restaurant_management.port;

import hust.project.restaurant_management.entity.WorkAttendanceEntity;
import hust.project.restaurant_management.entity.dto.request.GetWorkAttendanceRequest;

import java.util.List;

public interface IWorkAttendancePort {
    WorkAttendanceEntity save(WorkAttendanceEntity workAttendanceEntity);

    WorkAttendanceEntity getWorkAttendanceById(Long id);

    List<WorkAttendanceEntity> getAllWorkAttendances(GetWorkAttendanceRequest filter);

    List<WorkAttendanceEntity> getWorkAttendancesByWorkScheduleIds(List<Long> workScheduleIds);

    void deleteWorkAttendanceByWorkScheduleId(Long id);
}
