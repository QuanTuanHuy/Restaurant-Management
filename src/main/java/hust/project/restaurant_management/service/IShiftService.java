package hust.project.restaurant_management.service;

import hust.project.restaurant_management.entity.ShiftEntity;
import hust.project.restaurant_management.entity.dto.request.CreateShiftRequest;
import hust.project.restaurant_management.entity.dto.request.UpdateShiftRequest;

import java.util.List;

public interface IShiftService {
    ShiftEntity createShift(CreateShiftRequest request);

    List<ShiftEntity> getAllShifts();

    ShiftEntity getDetailShift(Long id);

    ShiftEntity updateShift(Long id, UpdateShiftRequest request);

    void deleteShift(Long id);
}
