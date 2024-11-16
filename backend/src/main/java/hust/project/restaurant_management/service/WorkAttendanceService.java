package hust.project.restaurant_management.service;

import hust.project.restaurant_management.entity.WorkAttendanceEntity;
import hust.project.restaurant_management.entity.dto.request.GetWorkAttendanceRequest;
import hust.project.restaurant_management.entity.dto.request.UpdateWorkAttendanceRequest;
import hust.project.restaurant_management.usecase.GetWorkAttendanceUseCase;
import hust.project.restaurant_management.usecase.UpdateWorkAttendanceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkAttendanceService implements IWorkAttendanceService {
    private final GetWorkAttendanceUseCase getWorkAttendanceUseCase;
    private final UpdateWorkAttendanceUseCase updateWorkAttendanceUseCase;

    @Override
    public List<WorkAttendanceEntity> getAllWorkAttendances(GetWorkAttendanceRequest filter) {
        return getWorkAttendanceUseCase.getAllWorkAttendances(filter);
    }

    @Override
    public WorkAttendanceEntity getDetailWorkAttendance(Long id) {
        return getWorkAttendanceUseCase.getDetailWorkAttendance(id);
    }

    @Override
    public WorkAttendanceEntity updateWorkAttendance(Long id, UpdateWorkAttendanceRequest request) {
        return updateWorkAttendanceUseCase.updateWorkAttendance(id, request);
    }
}
