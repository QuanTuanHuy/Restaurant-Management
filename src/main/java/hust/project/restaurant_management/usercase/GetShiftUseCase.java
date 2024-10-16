package hust.project.restaurant_management.usercase;

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
        return shiftPort.getAllShifts();
    }

    public ShiftEntity getDetailShift(Long id) {
        return shiftPort.getShiftById(id);
    }
}
