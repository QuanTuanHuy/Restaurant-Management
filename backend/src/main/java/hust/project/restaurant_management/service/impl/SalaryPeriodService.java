package hust.project.restaurant_management.service.impl;

import hust.project.restaurant_management.entity.SalaryPeriodEntity;
import hust.project.restaurant_management.entity.dto.request.CreateSalaryPeriodRequest;
import hust.project.restaurant_management.entity.dto.request.GetSalaryPeriodRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.service.ISalaryPeriodService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaryPeriodService implements ISalaryPeriodService {
    @Override
    public SalaryPeriodEntity createSalaryPeriod(CreateSalaryPeriodRequest request) {
        return null;
    }

    @Override
    public Pair<PageInfo, List<SalaryPeriodEntity>> getAllSalaryPeriods(GetSalaryPeriodRequest filter) {
        return null;
    }

    @Override
    public SalaryPeriodEntity getSalaryPeriodDetail(Long id) {
        return null;
    }

    @Override
    public void deleteSalaryPeriod(Long id) {

    }
}
