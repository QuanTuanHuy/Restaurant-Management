package hust.project.restaurant_management.repository.adapter;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.entity.WorkAttendanceEntity;
import hust.project.restaurant_management.entity.dto.request.GetWorkAttendanceRequest;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.mapper.IWorkAttendanceMapper;
import hust.project.restaurant_management.model.WorkAttendanceModel;
import hust.project.restaurant_management.port.IWorkAttendancePort;
import hust.project.restaurant_management.repository.IWorkAttendanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkAttendanceAdapter implements IWorkAttendancePort {
    private final IWorkAttendanceRepository workAttendanceRepository;
    private final IWorkAttendanceMapper workAttendanceMapper;

    @Override
    public WorkAttendanceEntity save(WorkAttendanceEntity workAttendanceEntity) {
        try {
            WorkAttendanceModel workAttendanceModel = workAttendanceMapper.toModelFromEntity(workAttendanceEntity);
            return workAttendanceMapper.toEntityFromModel(workAttendanceRepository.save(workAttendanceModel));
        } catch (Exception e) {
            log.error("[WorkAttendanceAdapter] save work attendance failed, error: {} ", e.getMessage());
            throw new AppException(ErrorCode.CREATE_WORK_ATTENDANCE_FAILED);
        }
    }

    @Override
    public WorkAttendanceEntity getWorkAttendanceById(Long id) {
        return workAttendanceMapper.toEntityFromModel(workAttendanceRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("[WorkAttendanceAdapter] get work attendance by id failed, {}", id);
                    return new AppException(ErrorCode.CREATE_WORK_ATTENDANCE_FAILED);
                }));
    }

    @Override
    public List<WorkAttendanceEntity> getAllWorkAttendances(GetWorkAttendanceRequest filter) {
        return workAttendanceMapper.toEntitiesFromModels(workAttendanceRepository.getAllWorkAttendances(filter));
    }

    @Override
    public List<WorkAttendanceEntity> getWorkAttendancesByWorkScheduleIds(List<Long> workScheduleIds) {
        return workAttendanceMapper.toEntitiesFromModels(workAttendanceRepository
                .findByWorkScheduleIdIn(workScheduleIds));
    }

    @Override
    public void deleteWorkAttendanceByWorkScheduleId(Long id) {
        try {
            workAttendanceRepository.deleteByWorkScheduleId(id);
        } catch (Exception e) {
            log.error("[WorkAttendanceAdapter] delete work attendance failed, error: {} ", e.getMessage());
            throw new AppException(ErrorCode.DELETE_WORK_SCHEDULE_FAILED);
        }
    }
}
