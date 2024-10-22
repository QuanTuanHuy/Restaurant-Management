package hust.project.restaurant_management.service;

import hust.project.restaurant_management.entity.SalaryPeriodEntity;
import hust.project.restaurant_management.entity.dto.request.CreateSalaryPeriodRequest;
import hust.project.restaurant_management.entity.dto.request.GetSalaryPeriodRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface ISalaryPeriodService {
    SalaryPeriodEntity createSalaryPeriod(CreateSalaryPeriodRequest request);

    Pair<PageInfo, List<SalaryPeriodEntity>> getAllSalaryPeriods(GetSalaryPeriodRequest filter);

    SalaryPeriodEntity getSalaryPeriodDetail(Long id);

    void deleteSalaryPeriod(Long id);
}
