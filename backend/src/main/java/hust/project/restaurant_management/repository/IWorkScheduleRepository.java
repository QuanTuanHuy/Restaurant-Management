package hust.project.restaurant_management.repository;

import hust.project.restaurant_management.model.WorkScheduleModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IWorkScheduleRepository extends IBaseRepository<WorkScheduleModel> {
    List<WorkScheduleModel> findByIdIn(List<Long> ids);
}
