package hust.project.restaurant_management.service.impl;

import hust.project.restaurant_management.entity.dto.request.GetStatisticByCustomerRequest;
import hust.project.restaurant_management.entity.dto.request.GetStatisticByMenuItemRequest;
import hust.project.restaurant_management.entity.dto.request.GetStatisticByRevenueRequest;
import hust.project.restaurant_management.entity.dto.response.*;
import hust.project.restaurant_management.service.IStatisticService;
import hust.project.restaurant_management.usecase.GetStatisticUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticService implements IStatisticService {
    private final GetStatisticUseCase getStatisticUseCase;

    @Override
    public StatisticByMenuItemResponse getStatisticByMenuItem(GetStatisticByMenuItemRequest request) {
        return getStatisticUseCase.getStatisticByMenuItem(request);
    }

    @Override
    public StatisticByCustomerAndDateResponse getStatisticByCustomerAndDate(GetStatisticByCustomerRequest request) {
        return getStatisticUseCase.getStatisticByCustomer(request);
    }

    @Override
    public StatisticByRevenueAndDateResponse getStatisticByRevenueAndDate(GetStatisticByRevenueRequest request) {
        return getStatisticUseCase.getStatisticByRevenue(request);
    }

    @Override
    public StatisticByRevenueAndHourResponse getStatisticByRevenueAndHour(GetStatisticByRevenueRequest request) {
        return getStatisticUseCase.getStatisticByRevenueAndHour(request);
    }

    @Override
    public StatisticByCustomerAndHourResponse getStatisticByCustomerAndHour(GetStatisticByCustomerRequest request) {
        return getStatisticUseCase.getStatisticByCustomerAndHour(request);
    }
}
