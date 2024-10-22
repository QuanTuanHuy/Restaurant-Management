package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.constants.SalaryPeriodStatusEnum;
import hust.project.restaurant_management.entity.SalaryDetailEntity;
import hust.project.restaurant_management.entity.SalaryPeriodEntity;
import hust.project.restaurant_management.entity.dto.request.CreateSalaryPeriodRequest;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.mapper.ISalaryPeriodMapper;
import hust.project.restaurant_management.port.ISalaryDetailPort;
import hust.project.restaurant_management.port.ISalaryPeriodPort;
import hust.project.restaurant_management.utils.GenerateCodeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateSalaryPeriodUseCase {
    private final ISalaryPeriodPort salaryPeriodPort;
    private final ISalaryPeriodMapper salaryPeriodMapper;
    private final ISalaryDetailPort salaryDetailPort;

    private final CalculateSalaryDetailUseCase calculateSalaryDetails;

    @Transactional
    public SalaryPeriodEntity createSalaryPeriod(CreateSalaryPeriodRequest request) {
        Boolean isExist = salaryPeriodPort.isExistSalaryPeriodInTime(request.getFromDate(), request.getToDate());

        if (isExist) {
            log.error("[CreateSalaryPeriodUseCase] create salary period failed, error: Salary period is exist in time");
            throw new AppException(ErrorCode.CREATE_SALARY_PERIOD_FAILED);
        }

        SalaryPeriodEntity salaryPeriod = salaryPeriodMapper.toEntityFromRequest(request);
        salaryPeriod.setCode(GenerateCodeUtils.generateCode("BL", salaryPeriodPort.getMaxId()) + 1);
        salaryPeriod.setStatus(SalaryPeriodStatusEnum.PROCESSING.name());

        var savedSalaryPeriod = salaryPeriodPort.save(salaryPeriod);

        List<SalaryDetailEntity> salaryDetails = calculateSalaryDetails.calculateSalaryDetails(savedSalaryPeriod);
        var savedSalaryDetails = salaryDetailPort.saveAll(salaryDetails);

        savedSalaryPeriod.setSalaryDetails(savedSalaryDetails);
        savedSalaryPeriod.setTotalSalary(savedSalaryDetails.stream().mapToDouble(SalaryDetailEntity::getTotalSalary).sum());
        savedSalaryPeriod.setPaidSalary(0.0);

        return salaryPeriodPort.save(savedSalaryPeriod);
    }
}
