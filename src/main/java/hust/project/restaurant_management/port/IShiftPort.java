package hust.project.restaurant_management.port;

import hust.project.restaurant_management.entity.ShiftEntity;

import java.util.List;

public interface IShiftPort {
    ShiftEntity save(ShiftEntity shiftEntity);

    List<ShiftEntity> getAllShifts();

    ShiftEntity getShiftById(Long id);

    void deleteShiftById(Long id);

    boolean isOverlapShift(ShiftEntity shiftEntity);
}
