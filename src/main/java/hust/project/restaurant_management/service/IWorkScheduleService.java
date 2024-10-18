package hust.project.restaurant_management.service;

import hust.project.restaurant_management.entity.WorkScheduleEntity;
import hust.project.restaurant_management.entity.dto.request.CreateWorkScheduleRequest;
import hust.project.restaurant_management.entity.dto.request.GetWorkScheduleRequest;

import java.util.List;

public interface IWorkScheduleService {
    WorkScheduleEntity createWorkSchedule(CreateWorkScheduleRequest request);

    List<WorkScheduleEntity> getAllWorkSchedules(GetWorkScheduleRequest filter);

    void deleteWorkSchedule(Long id);
}
