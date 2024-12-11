package hust.project.restaurant_management.repository.adapter;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.entity.ShiftEntity;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.mapper.IShiftMapper;
import hust.project.restaurant_management.model.ShiftModel;
import hust.project.restaurant_management.port.IShiftPort;
import hust.project.restaurant_management.repository.IShiftRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShiftAdapter implements IShiftPort {
    private final IShiftRepository shiftRepository;
    private final IShiftMapper shiftMapper;

    @Override
    public ShiftEntity save(ShiftEntity shiftEntity) {
        try {
            ShiftModel shiftModel = shiftMapper.toModelFromEntity(shiftEntity);
            return shiftMapper.toEntityFromModel(shiftRepository.save(shiftModel));
        } catch (Exception e) {
            log.error("[ShiftAdapter] save shift error: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_SHIFT_FAILED);
        }
    }

    @Override
    public List<ShiftEntity> getAllShifts() {
        return shiftMapper.toEntitiesFromModels(shiftRepository.findAll());
    }

    @Override
    public ShiftEntity getShiftById(Long id) {
        return shiftMapper.toEntityFromModel(shiftRepository.findById(id).orElseThrow(() -> {
            log.error("[ShiftAdapter] get shift by id error: {}", id);
            return new AppException(ErrorCode.SHIFT_NOT_FOUND);
        }));
    }

    @Override
    public void deleteShiftById(Long id) {
        try {
            shiftRepository.deleteById(id);
        } catch (Exception e) {
            log.error("[ShiftAdapter] delete shift by id error: {}", e.getMessage());
            throw new AppException(ErrorCode.DELETE_SHIFT_FAILED);
        }
    }

    @Override
    public boolean isOverlapShift(ShiftEntity shiftEntity) {
        Long currentId = shiftEntity.getId() == null ? -1 : shiftEntity.getId();
        List<ShiftModel> shiftModels = shiftRepository.findShiftOverlapping(currentId, shiftEntity.getStartTime(),
                shiftEntity.getEndTime());

        return !shiftModels.isEmpty();
    }
}
