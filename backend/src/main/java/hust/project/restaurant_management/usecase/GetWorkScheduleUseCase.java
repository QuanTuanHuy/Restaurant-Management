package hust.project.restaurant_management.usecase;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.entity.ShiftEntity;
import hust.project.restaurant_management.entity.UserEntity;
import hust.project.restaurant_management.entity.WorkScheduleEntity;
import hust.project.restaurant_management.entity.dto.request.GetWorkScheduleRequest;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.port.IShiftPort;
import hust.project.restaurant_management.port.IUserPort;
import hust.project.restaurant_management.port.IWorkSchedulePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetWorkScheduleUseCase {
    private final IWorkSchedulePort workSchedulePort;
    private final IUserPort userPort;
    private final IShiftPort shiftPort;

    public List<WorkScheduleEntity> getAllWorkSchedules(GetWorkScheduleRequest filter) {
        List<Long> userIds = filter.getUserIds();
        List<UserEntity> users = new ArrayList<>();

        if (!CollectionUtils.isEmpty(userIds)) {
            users = userPort.getUsersByIds(userIds);

            if (users.size() != userIds.size()) {
                log.error("[GetWorkScheduleUseCase] get all work schedules failed: user not found");
                throw new AppException(ErrorCode.USER_NOT_FOUND);
            }
        }

        List<WorkScheduleEntity> workSchedules = workSchedulePort.getAllWorkSchedules(filter);

        if (CollectionUtils.isEmpty(workSchedules)) {
            return List.of();
        }

        if (CollectionUtils.isEmpty(userIds)) {
            users = userPort.getUsersByIds(workSchedules.stream()
                    .map(WorkScheduleEntity::getUserId).distinct().toList());

        }

        var mapIdToUser = users.stream().collect(Collectors.toMap(UserEntity::getId, Function.identity()));


        List<ShiftEntity> shifts = shiftPort.getAllShifts();

        workSchedules.forEach(workSchedule -> {
            workSchedule.setUser(mapIdToUser.getOrDefault(workSchedule.getUserId(), null));
            workSchedule.setShift(shifts.stream()
                    .filter(shift -> shift.getId().equals(workSchedule.getShiftId()))
                    .findFirst().orElse(null));
        });

        return workSchedules;
    }

    public List<WorkScheduleEntity> getWorkSchedulesByIds(List<Long> ids) {
        var workSchedules = workSchedulePort.getWorkSchedulesByIds(ids);

        if (CollectionUtils.isEmpty(workSchedules)) {
            return List.of();
        }

        List<UserEntity> users = userPort.getUsersByIds(workSchedules.stream()
                .map(WorkScheduleEntity::getUserId)
                .distinct().toList());

        var mapIdToUser = users.stream().collect(Collectors.toMap(UserEntity::getId, Function.identity()));


        List<ShiftEntity> shifts = shiftPort.getAllShifts();


        workSchedules.forEach(workSchedule -> {
            workSchedule.setUser(mapIdToUser.getOrDefault(workSchedule.getUserId(), null));
            workSchedule.setShift(shifts.stream()
                    .filter(shift -> shift.getId().equals(workSchedule.getShiftId()))
                    .findFirst().orElse(null));
        });

        return workSchedules;
    }

    public WorkScheduleEntity getDetailWorkSchedule(Long id) {
        var workSchedule = workSchedulePort.getWorkScheduleById(id);

        workSchedule.setUser(userPort.getUserById(workSchedule.getUserId()));
        workSchedule.setShift(shiftPort.getShiftById(workSchedule.getShiftId()));

        return workSchedule;
    }

}
