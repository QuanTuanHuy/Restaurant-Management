package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.constants.SalaryTypeEnum;
import hust.project.restaurant_management.entity.*;
import hust.project.restaurant_management.entity.dto.request.GetWorkScheduleRequest;
import hust.project.restaurant_management.port.IUserPort;
import hust.project.restaurant_management.port.IWorkAttendancePort;
import hust.project.restaurant_management.port.IWorkSchedulePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalculateSalaryDetailUseCase {
    private final IUserPort userPort;
    private final IWorkSchedulePort workSchedulePort;
    private final IWorkAttendancePort workAttendancePort;

    public List<SalaryDetailEntity>     calculateSalaryDetails(SalaryPeriodEntity salaryPeriod) {
        List<UserEntity> users = userPort.getAllUsers().stream()
                .filter(user -> !user.getName().equals("admin")).toList();

        if (users.isEmpty()) {
            return List.of();
        }

        List<WorkScheduleEntity> workSchedules = workSchedulePort.getAllWorkSchedules(GetWorkScheduleRequest.builder()
                        .startDate(salaryPeriod.getFromDate())
                        .endDate(salaryPeriod.getToDate())
                        .userIds(users.stream().map(UserEntity::getId).toList())
                .build());


        List<Long> workScheduleIds = workSchedules.stream().map(WorkScheduleEntity::getId).toList();
        List<WorkAttendanceEntity> workAttendances = workAttendancePort.getWorkAttendancesByWorkScheduleIds(workScheduleIds);

        var mapWorkScheduleIdToWorkAttendance = workAttendances.stream()
                .collect(Collectors.toMap(WorkAttendanceEntity::getWorkScheduleId, Function.identity()));


        return users.stream().map(user -> {

            var salaryDetail = SalaryDetailEntity.builder()
                    .userId(user.getId())
                    .salaryPeriodId(salaryPeriod.getId())
                    .paidSalary(0.0)
                    .build();

            List<WorkScheduleEntity> currentWorkSchedules = workSchedules.stream()
                    .filter(workSchedule -> workSchedule.getUserId().equals(user.getId()))
                    .toList();

            AtomicReference<Long> minutes = new AtomicReference<>(0L);
            Set<Integer> days = new HashSet<>();

            currentWorkSchedules.forEach(workSchedule -> {
                var workAttendance = mapWorkScheduleIdToWorkAttendance.get(workSchedule.getId());
                if (workAttendance.getCheckInTime() != null && workAttendance.getCheckOutTime() != null) {
                    minutes.updateAndGet(v -> v + Duration.between(workAttendance.getCheckInTime(), workAttendance.getCheckOutTime()).toMinutes());

                    // chua tinh den 1 ngay co di lam du hay khong(voi truong hop nhieu ca)
                    days.add(workSchedule.getDate().getDayOfYear());
                }
            });

            salaryDetail.setTotalWorkingDays((long) days.size());
            salaryDetail.setTotalWorkingHours((minutes.get() + 59) / 60);

            if (user.getSalaryType() == null) {
                return salaryDetail;
            }


            if (user.getSalaryType().equals(SalaryTypeEnum.HOURLY.name())) {
                salaryDetail.setTotalSalary(salaryDetail.getTotalWorkingHours() * user.getSalaryPerHour());
            } else if (user.getSalaryType().equals(SalaryTypeEnum.DAILY.name())) {
                salaryDetail.setTotalSalary(user.getSalaryPerMonth() / 22 * salaryDetail.getTotalWorkingDays());
            }

            return salaryDetail;
        }).toList();
    }

}