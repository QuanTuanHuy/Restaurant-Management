package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.port.ISalaryDetailPort;
import hust.project.restaurant_management.port.ISalaryPeriodPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteSalaryPeriodUseCase {
    private final ISalaryPeriodPort salaryPeriodPort;
    private final ISalaryDetailPort salaryDetailPort;

    @Transactional
    public void deleteSalaryPeriod(Long id) {
        salaryPeriodPort.getSalaryPeriodById(id);

        salaryDetailPort.deleteSalaryDetailsBySalaryPeriodId(id);
        salaryPeriodPort.deleteSalaryPeriod(id);
    }
}
