package hust.project.restaurant_management.repository.adapter;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.entity.WorkScheduleEntity;
import hust.project.restaurant_management.entity.dto.request.GetWorkScheduleRequest;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.mapper.IWorkScheduleMapper;
import hust.project.restaurant_management.model.WorkScheduleModel;
import hust.project.restaurant_management.port.IWorkSchedulePort;
import hust.project.restaurant_management.repository.IWorkScheduleRepository;
import hust.project.restaurant_management.repository.specification.WorkScheduleSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkScheduleAdapter implements IWorkSchedulePort {
    private final IWorkScheduleRepository workScheduleRepository;
    private final IWorkScheduleMapper workScheduleMapper;

    @Override
    public WorkScheduleEntity save(WorkScheduleEntity workScheduleEntity) {
        try {
            WorkScheduleModel workScheduleModel = workScheduleMapper.toModelFromEntity(workScheduleEntity);
            return workScheduleMapper.toEntityFromModel(workScheduleRepository.save(workScheduleModel));
        } catch (Exception e) {
            log.error("[WorkScheduleAdapter] save work schedule failed: error, {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_WORK_SCHEDULE_FAILED);
        }
    }

    @Override
    public WorkScheduleEntity getWorkScheduleById(Long id) {
        return workScheduleMapper.toEntityFromModel(workScheduleRepository.findById(id).orElseThrow(
                () -> {
                    log.error("[WorkScheduleAdapter] get work schedule by id failed, {}", id);
                    return new AppException(ErrorCode.WORK_SCHEDULE_NOT_FOUND);
                }
        ));
    }

    @Override
    public List<WorkScheduleEntity> getAllWorkSchedules(GetWorkScheduleRequest filter) {
        return workScheduleMapper.toEntitiesFromModels(
                workScheduleRepository.findAll(WorkScheduleSpecification.getAllWorkSchedules(filter))
        );
    }

    @Override
    public List<WorkScheduleEntity> getWorkSchedulesByIds(List<Long> ids) {
        return workScheduleMapper.toEntitiesFromModels(workScheduleRepository.findByIdIn(ids));
    }

    @Override
    public void deleteWorkScheduleById(Long id) {
        try {
            workScheduleRepository.deleteById(id);
        } catch (Exception e) {
            log.error("[WorkScheduleAdapter] delete work schedule failed: error, {}", e.getMessage());
            throw new AppException(ErrorCode.DELETE_WORK_SCHEDULE_FAILED);
        }
    }
}
