package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.entity.SalaryDetailEntity;
import hust.project.restaurant_management.entity.SalaryPeriodEntity;
import hust.project.restaurant_management.port.ISalaryDetailPort;
import hust.project.restaurant_management.port.ISalaryPeriodPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AsyncCalculateSalaryPeriodUseCase {
    private final ISalaryPeriodPort salaryPeriodPort;
    private final ISalaryDetailPort salaryDetailPort;

    private final CalculateSalaryDetailUseCase calculateSalaryDetailUseCase;

    @Transactional
    public SalaryPeriodEntity asyncCalculateSalaryPeriod(Long id) {
        SalaryPeriodEntity salaryPeriod = salaryPeriodPort.getSalaryPeriodById(id);

        List<SalaryDetailEntity> salaryDetails = salaryDetailPort.getSalaryDetailsBySalaryPeriodId(id);


        List<SalaryDetailEntity> newSalaryDetails = calculateSalaryDetailUseCase.calculateSalaryDetails(salaryPeriod);
        var mapUserIdToSalaryDetail = newSalaryDetails.stream()
                .collect(Collectors.toMap(SalaryDetailEntity::getUserId, Function.identity()));


        salaryDetails.forEach(salaryDetail -> {
            var newSalaryDetail = mapUserIdToSalaryDetail.getOrDefault(salaryDetail.getUserId(), null);

            if (newSalaryDetail == null) {
                return;
            }

            salaryDetail.setTotalSalary(newSalaryDetail.getTotalSalary());
            salaryDetail.setTotalWorkingDays(newSalaryDetail.getTotalWorkingDays());
            salaryDetail.setTotalWorkingHours(newSalaryDetail.getTotalWorkingHours());

        });

        salaryDetailPort.saveAll(salaryDetails);

        salaryPeriod.setSalaryDetails(salaryDetails);
        salaryPeriod.setTotalSalary(salaryDetails.stream().mapToDouble(SalaryDetailEntity::getTotalSalary).sum());

        return salaryPeriod;
    }
}
