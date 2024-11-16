package hust.project.restaurant_management.usecase;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.entity.ShiftEntity;
import hust.project.restaurant_management.entity.dto.request.CreateShiftRequest;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.mapper.IShiftMapper;
import hust.project.restaurant_management.port.IShiftPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateShiftUseCase {
    private final IShiftPort shiftPort;
    private final IShiftMapper shiftMapper;

    @Transactional
    public ShiftEntity createShift(CreateShiftRequest request) {
        ShiftEntity shift = shiftMapper.toEntityFromRequest(request);

        if (shiftPort.isOverlapShift(shift)) {
            log.error("[CreateShiftUseCase] create shift error: shift is overlap");
            throw new AppException(ErrorCode.CREATE_SHIFT_FAILED);
        }

        return shiftPort.save(shift);
    }
}
