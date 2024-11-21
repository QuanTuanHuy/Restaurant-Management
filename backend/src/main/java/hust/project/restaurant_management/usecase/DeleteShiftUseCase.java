package hust.project.restaurant_management.usecase;

import hust.project.restaurant_management.constants.ShiftStatusEnum;
import hust.project.restaurant_management.port.IShiftPort;
import hust.project.restaurant_management.port.IWorkSchedulePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteShiftUseCase {
    private final IShiftPort shiftPort;
    private final IWorkSchedulePort workSchedulePort;

    @Transactional
    public void deleteShift(Long id) {
        var shift = shiftPort.getShiftById(id);

        boolean isWorkScheduleExist = workSchedulePort.isWorkScheduleExistByShiftId(id);
        if (isWorkScheduleExist) {
            shift.setStatus(ShiftStatusEnum.INACTIVE.name());
            shiftPort.save(shift);
            return;
        }

        shiftPort.deleteShiftById(id);
    }
}
