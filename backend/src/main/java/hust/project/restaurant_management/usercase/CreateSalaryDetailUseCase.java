package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.constants.SalaryTypeEnum;
import hust.project.restaurant_management.entity.*;
import hust.project.restaurant_management.entity.dto.request.GetWorkScheduleRequest;
import hust.project.restaurant_management.port.ISalaryDetailPort;
import hust.project.restaurant_management.port.IUserPort;
import hust.project.restaurant_management.port.IWorkAttendancePort;
import hust.project.restaurant_management.port.IWorkSchedulePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreateSalaryDetailUseCase {
    private final ISalaryDetailPort salaryDetailPort;
    private final IUserPort userPort;
    private final IWorkSchedulePort workSchedulePort;
    private final IWorkAttendancePort workAttendancePort;

    public List<SalaryDetailEntity> createSalaryDetails(SalaryPeriodEntity salaryPeriod) {
        List<UserEntity> users = userPort.getAllUsers();

        if (users.isEmpty()) {
            return List.of();
        }

        List<WorkScheduleEntity> workSchedules = workSchedulePort.getAllWorkSchedules(GetWorkScheduleRequest.builder()
                        .startDate(salaryPeriod.getFromDate())
                        .endDate(salaryPeriod.getToDate())
                .build());

        if (workSchedules.isEmpty()) {
            return List.of();
        }

        List<Long> workScheduleIds = workSchedules.stream().map(WorkScheduleEntity::getId).toList();
        List<WorkAttendanceEntity> workAttendances = workAttendancePort.getWorkAttendancesByWorkScheduleIds(workScheduleIds);

        var mapWorkScheduleIdToWorkAttendance = workAttendances.stream()
                .collect(Collectors.toMap(WorkAttendanceEntity::getWorkScheduleId, Function.identity()));


        List<SalaryDetailEntity> salaryDetails = users.stream().map(user -> {
            var salaryDetail = SalaryDetailEntity.builder()
                    .userId(user.getId())
                    .salaryPeriodId(salaryPeriod.getId())
                    .build();
            List<WorkScheduleEntity> currentWorkSchedules = workSchedules.stream()
                    .filter(workSchedule -> workSchedule.getUserId().equals(user.getId()))
                    .toList();

            AtomicReference<Long> minutes = new AtomicReference<>(0L);
            Set<Integer> days = new HashSet<>();

            currentWorkSchedules.forEach(workSchedule -> {
                var workAttendance = mapWorkScheduleIdToWorkAttendance.get(workSchedule.getId());
                if (workAttendance.getCheckInTime() != null && workAttendance.getCheckOutTime() != null) {
                    minutes.updateAndGet(v -> v + workAttendance.getCheckOutTime().getMinute() - workAttendance.getCheckInTime().getMinute());

                    // chua tinh den 1 ngay co di lam du hay khong(voi truong hop nhieu ca)
                    days.add(workSchedule.getDate().getDayOfYear());
                }
            });

            salaryDetail.setTotalWorkingDays((long) days.size());
            salaryDetail.setTotalWorkingHours((minutes.get() + 59) / 60);

            if (user.getSalaryType().equals(SalaryTypeEnum.HOURLY.name())) {
                salaryDetail.setTotalSalary(salaryDetail.getTotalWorkingHours() * user.getSalaryPerHour());
            } else if (user.getSalaryType().equals(SalaryTypeEnum.DAILY.name())) {
                salaryDetail.setTotalSalary(user.getSalaryPerMonth() / 22 * salaryDetail.getTotalWorkingDays());
            }

            return salaryDetail;
        }).toList();

        return salaryDetailPort.saveAll(salaryDetails);
    }
}
