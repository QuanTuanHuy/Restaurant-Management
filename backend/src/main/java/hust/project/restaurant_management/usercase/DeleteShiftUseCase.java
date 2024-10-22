package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.port.IShiftPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteShiftUseCase {
    private final IShiftPort shiftPort;

    @Transactional
    public void deleteShift(Long id) {
        shiftPort.deleteShiftById(id);
    }
}
