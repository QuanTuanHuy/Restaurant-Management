package hust.project.restaurant_management.service.impl;

import hust.project.restaurant_management.entity.dto.request.GetStatisticByCustomerRequest;
import hust.project.restaurant_management.entity.dto.request.GetStatisticByMenuItemRequest;
import hust.project.restaurant_management.entity.dto.request.GetStatisticByRevenueRequest;
import hust.project.restaurant_management.entity.dto.response.StatisticByCustomerResponse;
import hust.project.restaurant_management.entity.dto.response.StatisticByMenuItemResponse;
import hust.project.restaurant_management.entity.dto.response.StatisticByRevenueResponse;
import hust.project.restaurant_management.service.IStatisticService;
import hust.project.restaurant_management.usercase.GetStatisticByCustomerUseCase;
import hust.project.restaurant_management.usercase.GetStatisticByMenuItemUseCase;
import hust.project.restaurant_management.usercase.GetStatisticByRevenueUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticService implements IStatisticService {
    private final GetStatisticByRevenueUseCase getStatisticByRevenueUseCase;
    private final GetStatisticByCustomerUseCase getStatisticByCustomerUseCase;
    private final GetStatisticByMenuItemUseCase getStatisticByMenuItemUseCase;

    @Override
    public StatisticByMenuItemResponse getStatisticByMenuItem(GetStatisticByMenuItemRequest request) {
        return getStatisticByMenuItemUseCase.getStatisticByMenuItem(request);
    }

    @Override
    public StatisticByCustomerResponse getStatisticByCustomer(GetStatisticByCustomerRequest request) {
        return getStatisticByCustomerUseCase.getStatisticByCustomer(request);
    }

    @Override
    public StatisticByRevenueResponse getStatisticByRevenue(GetStatisticByRevenueRequest request) {
        return getStatisticByRevenueUseCase.getStatisticByRevenue(request);
    }
}
