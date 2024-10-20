package hust.project.restaurant_management.service;

import hust.project.restaurant_management.entity.dto.request.GetStatisticByCustomerRequest;
import hust.project.restaurant_management.entity.dto.request.GetStatisticByMenuItemRequest;
import hust.project.restaurant_management.entity.dto.request.GetStatisticByRevenueRequest;
import hust.project.restaurant_management.entity.dto.response.StatisticByCustomerResponse;
import hust.project.restaurant_management.entity.dto.response.StatisticByMenuItemResponse;
import hust.project.restaurant_management.entity.dto.response.StatisticByRevenueResponse;

public interface IStatisticService {
    StatisticByMenuItemResponse getStatisticByMenuItem(GetStatisticByMenuItemRequest request);

    StatisticByCustomerResponse getStatisticByCustomer(GetStatisticByCustomerRequest request);

    StatisticByRevenueResponse getStatisticByRevenue(GetStatisticByRevenueRequest request);
}
