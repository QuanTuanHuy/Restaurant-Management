package hust.project.restaurant_management.usecase;

import hust.project.restaurant_management.entity.WorkAttendanceEntity;
import hust.project.restaurant_management.entity.WorkScheduleEntity;
import hust.project.restaurant_management.entity.dto.request.GetWorkAttendanceRequest;
import hust.project.restaurant_management.port.IWorkAttendancePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetWorkAttendanceUseCase {
    private final IWorkAttendancePort workAttendancePort;
    private final GetWorkScheduleUseCase getWorkScheduleUseCase;

    public List<WorkAttendanceEntity> getAllWorkAttendances(GetWorkAttendanceRequest filter) {
        var workAttendances = workAttendancePort.getAllWorkAttendances(filter);

        List<WorkScheduleEntity> workSchedules = getWorkScheduleUseCase.getWorkSchedulesByIds(
                workAttendances.stream().map(WorkAttendanceEntity::getWorkScheduleId).toList()
        );

        var mapIdToWorkSchedule = workSchedules.stream()
                .collect(Collectors.toMap(WorkScheduleEntity::getId, Function.identity()));


        workAttendances.forEach(workAttendance -> workAttendance.setWorkSchedule(
                mapIdToWorkSchedule.getOrDefault(workAttendance.getWorkScheduleId(), null)));

        return workAttendances;
    }

    public WorkAttendanceEntity getDetailWorkAttendance(Long id) {
        var workAttendance = workAttendancePort.getWorkAttendanceById(id);

        workAttendance.setWorkSchedule(getWorkScheduleUseCase.getDetailWorkSchedule(workAttendance.getWorkScheduleId()));

        return workAttendance;
    }
}
