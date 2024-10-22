package hust.project.restaurant_management.service.impl;

import hust.project.restaurant_management.entity.SalaryPeriodEntity;
import hust.project.restaurant_management.entity.dto.request.CreateSalaryPeriodRequest;
import hust.project.restaurant_management.entity.dto.request.GetSalaryPeriodRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.service.ISalaryPeriodService;
import hust.project.restaurant_management.usercase.AsyncCalculateSalaryPeriodUseCase;
import hust.project.restaurant_management.usercase.CreateSalaryPeriodUseCase;
import hust.project.restaurant_management.usercase.DeleteSalaryPeriodUseCase;
import hust.project.restaurant_management.usercase.GetSalaryPeriodUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaryPeriodService implements ISalaryPeriodService {
    private final CreateSalaryPeriodUseCase createSalaryPeriodUseCase;
    private final GetSalaryPeriodUseCase getSalaryPeriodUseCase;
    private final DeleteSalaryPeriodUseCase deleteSalaryPeriodUseCase;
    private final AsyncCalculateSalaryPeriodUseCase asyncCalculateSalaryPeriodUseCase;

    @Override
    public SalaryPeriodEntity createSalaryPeriod(CreateSalaryPeriodRequest request) {
        return createSalaryPeriodUseCase.createSalaryPeriod(request);
    }

    @Override
    public Pair<PageInfo, List<SalaryPeriodEntity>> getAllSalaryPeriods(GetSalaryPeriodRequest filter) {
        return getSalaryPeriodUseCase.getAllSalaryPeriods(filter);
    }

    @Override
    public SalaryPeriodEntity getDetailSalaryPeriod(Long id) {
        return getSalaryPeriodUseCase.getSalaryPeriodDetail(id);
    }

    @Override
    public SalaryPeriodEntity asyncCalculateSalaryPeriod(Long id) {
        return asyncCalculateSalaryPeriodUseCase.asyncCalculateSalaryPeriod(id);
    }

    @Override
    public void deleteSalaryPeriod(Long id) {
        deleteSalaryPeriodUseCase.deleteSalaryPeriod(id);
    }
}
