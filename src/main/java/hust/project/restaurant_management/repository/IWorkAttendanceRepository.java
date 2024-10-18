package hust.project.restaurant_management.repository;

import hust.project.restaurant_management.model.WorkAttendanceModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IWorkAttendanceRepository extends IBaseRepository<WorkAttendanceModel>, CustomWorkAttendanceRepository {
    List<WorkAttendanceModel> findByWorkScheduleIdIn(List<Long> workScheduleIds);

    void deleteByWorkScheduleId(Long workScheduleId);
}
