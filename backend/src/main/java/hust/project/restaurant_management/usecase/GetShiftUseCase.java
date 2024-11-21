package hust.project.restaurant_management.usecase;

import hust.project.restaurant_management.constants.ShiftStatusEnum;
import hust.project.restaurant_management.entity.ShiftEntity;
import hust.project.restaurant_management.port.IShiftPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetShiftUseCase {
    private final IShiftPort shiftPort;

    public List<ShiftEntity> getAllShifts() {
        return shiftPort.getAllShifts()
                .stream()
                .filter(shift -> shift.getStatus().equals(ShiftStatusEnum.ACTIVE.name()))
                .toList();
    }

    public ShiftEntity getDetailShift(Long id) {
        return shiftPort.getShiftById(id);
    }
}
