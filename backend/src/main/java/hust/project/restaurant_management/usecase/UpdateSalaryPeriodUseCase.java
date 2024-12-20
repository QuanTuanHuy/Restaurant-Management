package hust.project.restaurant_management.usecase;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.constants.PaymentMethodEnum;
import hust.project.restaurant_management.constants.SalaryDetailStatusEnum;
import hust.project.restaurant_management.constants.SalaryPeriodStatusEnum;
import hust.project.restaurant_management.entity.SalaryDetailEntity;
import hust.project.restaurant_management.entity.SalaryPeriodEntity;
import hust.project.restaurant_management.entity.dto.request.PaymentSalaryDetailRequest;
import hust.project.restaurant_management.entity.dto.request.PaymentSalaryPeriodRequest;
import hust.project.restaurant_management.entity.dto.request.UpdateSalaryPeriodStatusRequest;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.port.ISalaryDetailPort;
import hust.project.restaurant_management.port.ISalaryPeriodPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateSalaryPeriodUseCase {
    private final ISalaryPeriodPort salaryPeriodPort;
    private final ISalaryDetailPort salaryDetailPort;
    private final CalculateSalaryDetailUseCase calculateSalaryDetailUseCase;

    @Transactional
    public SalaryPeriodEntity asyncCalculateSalaryPeriod(Long id) {
        SalaryPeriodEntity salaryPeriod = salaryPeriodPort.getSalaryPeriodById(id);

        validateSalaryPeriodNotDone(salaryPeriod);

        List<SalaryDetailEntity> salaryDetails = salaryDetailPort.getSalaryDetailsBySalaryPeriodId(id);
        var mapUserIdToSalaryDetail = salaryDetails.stream()
                .collect(Collectors.toMap(SalaryDetailEntity::getUserId, Function.identity()));


        List<SalaryDetailEntity> newSalaryDetails = calculateSalaryDetailUseCase.calculateSalaryDetails(salaryPeriod);

        List<SalaryDetailEntity> notExistedSalaryDetails = new ArrayList<>();


        newSalaryDetails.forEach(newSalaryDetail -> {
            var currentSalaryDetail = mapUserIdToSalaryDetail.getOrDefault(newSalaryDetail.getUserId(), null);

            if (ObjectUtils.isEmpty(currentSalaryDetail)) {
                notExistedSalaryDetails.add(newSalaryDetail);
            }

            currentSalaryDetail.setTotalSalary(newSalaryDetail.getTotalSalary());
            currentSalaryDetail.setTotalWorkingDays(newSalaryDetail.getTotalWorkingDays());
            currentSalaryDetail.setTotalWorkingHours(newSalaryDetail.getTotalWorkingHours());

            if (currentSalaryDetail.getTotalSalary() > currentSalaryDetail.getPaidSalary()) {
                currentSalaryDetail.setStatus(SalaryDetailStatusEnum.PROCESSING.name());
            }

        });

        salaryDetailPort.saveAll(salaryDetails);

        if (!CollectionUtils.isEmpty(notExistedSalaryDetails)) {
            salaryDetailPort.saveAll(notExistedSalaryDetails);
        }

        salaryPeriod.setTotalSalary(newSalaryDetails.stream().mapToDouble(SalaryDetailEntity::getTotalSalary).sum());
        var savedSalaryPeriod =  salaryPeriodPort.save(salaryPeriod);
        savedSalaryPeriod.setSalaryDetails(newSalaryDetails);

        return savedSalaryPeriod;
    }

    @Transactional
    public SalaryPeriodEntity paymentSalaryPeriod(Long id, PaymentSalaryPeriodRequest request) {
        SalaryPeriodEntity salaryPeriod = salaryPeriodPort.getSalaryPeriodById(id);

        validateSalaryPeriodNotDone(salaryPeriod);

        List<Long> salaryDetailIds = request.getPaymentSalaryDetails().stream()
                .map(PaymentSalaryDetailRequest::getSalaryDetailId)
                .toList();
        List<SalaryDetailEntity> salaryDetails = salaryDetailPort.getSalaryDetailsByIdsAndSalaryPeriodId(salaryDetailIds, id);

        if (salaryDetails.size() != salaryDetailIds.size()) {
            log.error("[UpdateSalaryPeriodUseCase] payment salary period failed, error: Salary detail not found");
            throw new AppException(ErrorCode.UPDATE_SALARY_PERIOD_FAILED);
        }

        var mapSalaryIdToPaymentSalaryDetail = request.getPaymentSalaryDetails().stream()
                .collect(Collectors.toMap(PaymentSalaryDetailRequest::getSalaryDetailId, Function.identity()));

        String paymentMethod = PaymentMethodEnum.valueOf(request.getPaymentMethod()).name();

        salaryDetails.forEach(salaryDetail -> {

            if (salaryDetail.getStatus().equals(SalaryDetailStatusEnum.PAID.name())) {
                log.error("[UpdateSalaryPeriodUseCase] payment salary period failed, error: Salary detail is paid");
                throw new AppException(ErrorCode.UPDATE_SALARY_PERIOD_FAILED);
            }

            var totalPaidSalary = salaryDetail.getPaidSalary() +
                    mapSalaryIdToPaymentSalaryDetail.get(salaryDetail.getId()).getPaidSalary();

            salaryDetail.setPaidSalary(totalPaidSalary);
            salaryDetail.setPaymentMethod(paymentMethod);

            if (totalPaidSalary >= salaryDetail.getTotalSalary()) {
                salaryDetail.setStatus(SalaryDetailStatusEnum.PAID.name());
            }
        });
        salaryDetailPort.saveAll(salaryDetails);


        Double paidSalary = request.getPaymentSalaryDetails().stream()
                .mapToDouble(PaymentSalaryDetailRequest::getPaidSalary)
                .sum();
        salaryPeriod.setPaidSalary(salaryPeriod.getPaidSalary() + paidSalary);

        var savedSalaryPeriod =  salaryPeriodPort.save(salaryPeriod);
        savedSalaryPeriod.setSalaryDetails(salaryDetailPort.getSalaryDetailsBySalaryPeriodId(savedSalaryPeriod.getId()));

        return savedSalaryPeriod;
    }

    @Transactional
    public void updateSalaryPeriodStatus(Long id, UpdateSalaryPeriodStatusRequest request) {
        SalaryPeriodEntity salaryPeriod = salaryPeriodPort.getSalaryPeriodById(id);

        validateSalaryPeriodNotDone(salaryPeriod);

        salaryPeriod.setStatus(SalaryPeriodStatusEnum.valueOf(request.getStatus()).name());
        salaryPeriodPort.save(salaryPeriod);
    }

    private void validateSalaryPeriodNotDone(SalaryPeriodEntity salaryPeriod) {
        if (salaryPeriod.getStatus().equals(SalaryPeriodStatusEnum.DONE.name())) {
            log.error("[UpdateSalaryPeriodUseCase] update salary period failed, error: Salary period is done");
            throw new AppException(ErrorCode.UPDATE_SALARY_PERIOD_FAILED);
        }
    }
}
