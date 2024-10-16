package hust.project.restaurant_management.service.impl;

import hust.project.restaurant_management.entity.ShiftEntity;
import hust.project.restaurant_management.entity.dto.request.CreateShiftRequest;
import hust.project.restaurant_management.entity.dto.request.UpdateShiftRequest;
import hust.project.restaurant_management.service.IShiftService;
import hust.project.restaurant_management.usercase.CreateShiftUseCase;
import hust.project.restaurant_management.usercase.DeleteShiftUseCase;
import hust.project.restaurant_management.usercase.GetShiftUseCase;
import hust.project.restaurant_management.usercase.UpdateShiftUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShiftService implements IShiftService {
    private final CreateShiftUseCase createShiftUseCase;
    private final GetShiftUseCase getShiftUseCase;
    private final DeleteShiftUseCase deleteShiftUseCase;
    private final UpdateShiftUseCase updateShiftUseCase;

    @Override
    public ShiftEntity createShift(CreateShiftRequest request) {
        return createShiftUseCase.createShift(request);
    }

    @Override
    public List<ShiftEntity> getAllShifts() {
        return getShiftUseCase.getAllShifts();
    }

    @Override
    public ShiftEntity getDetailShift(Long id) {
        return getShiftUseCase.getDetailShift(id);
    }

    @Override
    public ShiftEntity updateShift(Long id, UpdateShiftRequest request) {
        return updateShiftUseCase.updateShift(id, request);
    }

    @Override
    public void deleteShift(Long id) {
        deleteShiftUseCase.deleteShift(id);
    }
}
