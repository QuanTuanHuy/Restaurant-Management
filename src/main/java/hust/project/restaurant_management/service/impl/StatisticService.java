package hust.project.restaurant_management.service.impl;

import hust.project.restaurant_management.entity.dto.request.GetStatisticByCustomerRequest;
import hust.project.restaurant_management.entity.dto.request.GetStatisticByMenuItemRequest;
import hust.project.restaurant_management.entity.dto.request.GetStatisticByRevenueRequest;
import hust.project.restaurant_management.entity.dto.response.StatisticByCustomerResponse;
import hust.project.restaurant_management.entity.dto.response.StatisticByMenuItemResponse;
import hust.project.restaurant_management.entity.dto.response.StatisticByRevenueResponse;
import hust.project.restaurant_management.service.IStatisticService;
import hust.project.restaurant_management.usercase.GetStatisticUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticService implements IStatisticService {
    private final GetStatisticUseCase getStatisticByMenuItem;

    @Override
    public StatisticByMenuItemResponse getStatisticByMenuItem(GetStatisticByMenuItemRequest request) {
        return getStatisticByMenuItem.getStatisticByMenuItem(request);
    }

    @Override
    public StatisticByCustomerResponse getStatisticByCustomer(GetStatisticByCustomerRequest request) {
        return getStatisticByMenuItem.getStatisticByCustomer(request);
    }

    @Override
    public StatisticByRevenueResponse getStatisticByRevenue(GetStatisticByRevenueRequest request) {
        return getStatisticByMenuItem.getStatisticByRevenue(request);
    }
}
