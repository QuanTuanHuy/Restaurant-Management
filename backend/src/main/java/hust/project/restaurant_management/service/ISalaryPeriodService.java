package hust.project.restaurant_management.service;

import hust.project.restaurant_management.entity.SalaryPeriodEntity;
import hust.project.restaurant_management.entity.dto.request.CreateSalaryPeriodRequest;
import hust.project.restaurant_management.entity.dto.request.GetSalaryPeriodRequest;
import hust.project.restaurant_management.entity.dto.request.PaymentSalaryPeriodRequest;
import hust.project.restaurant_management.entity.dto.request.UpdateSalaryPeriodStatusRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface ISalaryPeriodService {
    SalaryPeriodEntity createSalaryPeriod(CreateSalaryPeriodRequest request);

    Pair<PageInfo, List<SalaryPeriodEntity>> getAllSalaryPeriods(GetSalaryPeriodRequest filter);

    SalaryPeriodEntity getDetailSalaryPeriod(Long id);

    SalaryPeriodEntity paymentSalaryPeriod(Long id, PaymentSalaryPeriodRequest request);

    SalaryPeriodEntity asyncCalculateSalaryPeriod(Long id);

    void updateSalaryPeriodStatus(Long id, UpdateSalaryPeriodStatusRequest request);

    void deleteSalaryPeriod(Long id);
}
