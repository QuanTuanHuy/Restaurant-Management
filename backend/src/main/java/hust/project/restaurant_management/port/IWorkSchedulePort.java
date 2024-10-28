package hust.project.restaurant_management.port;

import hust.project.restaurant_management.entity.WorkScheduleEntity;
import hust.project.restaurant_management.entity.dto.request.GetWorkScheduleRequest;

import java.util.List;

public interface IWorkSchedulePort {
    WorkScheduleEntity save(WorkScheduleEntity workScheduleEntity);

    WorkScheduleEntity getWorkScheduleById(Long id);

    List<WorkScheduleEntity> getAllWorkSchedules(GetWorkScheduleRequest filter);

    List<WorkScheduleEntity> getWorkSchedulesByIds(List<Long> ids);

    boolean isWorkScheduleExistByShiftId(Long shiftId);

    void deleteWorkScheduleById(Long id);

}
