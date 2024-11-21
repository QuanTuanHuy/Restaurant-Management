package hust.project.restaurant_management.usecase;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.entity.ShiftEntity;
import hust.project.restaurant_management.entity.dto.request.UpdateShiftRequest;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.port.IShiftPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateShiftUseCase {
    private final IShiftPort shiftPort;

    @Transactional
    public ShiftEntity updateShift(Long id, UpdateShiftRequest request) {
        ShiftEntity shift = shiftPort.getShiftById(id);

        shift.setName(request.getName());
        shift.setStartTime(request.getStartTime());
        shift.setEndTime(request.getEndTime());

        if (shiftPort.isOverlapShift(shift)) {
            log.error("[UpdateShiftUseCase] update shift error: shift is overlap");
            throw new AppException(ErrorCode.UPDATE_SHIFT_FAILED);
        }

        return shiftPort.save(shift);
    }
}
