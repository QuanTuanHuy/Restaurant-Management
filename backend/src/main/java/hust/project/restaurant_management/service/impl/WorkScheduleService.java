package hust.project.restaurant_management.service.impl;

import hust.project.restaurant_management.entity.WorkScheduleEntity;
import hust.project.restaurant_management.entity.dto.request.CreateWorkScheduleRequest;
import hust.project.restaurant_management.entity.dto.request.GetWorkScheduleRequest;
import hust.project.restaurant_management.service.IWorkScheduleService;
import hust.project.restaurant_management.usercase.CreateWorkScheduleUseCase;
import hust.project.restaurant_management.usercase.DeleteWorkScheduleUseCase;
import hust.project.restaurant_management.usercase.GetWorkScheduleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkScheduleService implements IWorkScheduleService {
    private final CreateWorkScheduleUseCase createWorkScheduleUseCase;
    private final GetWorkScheduleUseCase getWorkScheduleUseCase;
    private final DeleteWorkScheduleUseCase deleteWorkScheduleUseCase;

    @Override
    public WorkScheduleEntity createWorkSchedule(CreateWorkScheduleRequest request) {
        return createWorkScheduleUseCase.createWorkSchedule(request);
    }

    @Override
    public List<WorkScheduleEntity> getAllWorkSchedules(GetWorkScheduleRequest filter) {
        return getWorkScheduleUseCase.getAllWorkSchedules(filter);
    }

    @Override
    public void deleteWorkSchedule(Long id) {
        deleteWorkScheduleUseCase.deleteWorkSchedule(id);
    }
}
