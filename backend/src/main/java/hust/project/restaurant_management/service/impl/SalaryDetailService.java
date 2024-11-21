package hust.project.restaurant_management.service.impl;

import hust.project.restaurant_management.entity.SalaryDetailEntity;
import hust.project.restaurant_management.entity.dto.request.GetSalaryDetailRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.service.ISalaryDetailService;
import hust.project.restaurant_management.usecase.GetSalaryDetailUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaryDetailService implements ISalaryDetailService {
    private final GetSalaryDetailUseCase getSalaryDetailUseCase;

    @Override
    public Pair<PageInfo, List<SalaryDetailEntity>> getAllSalaryDetails(GetSalaryDetailRequest filter) {
        return getSalaryDetailUseCase.getAllSalaryDetails(filter);
    }
}
