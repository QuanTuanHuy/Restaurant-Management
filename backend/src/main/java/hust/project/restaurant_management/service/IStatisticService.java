package hust.project.restaurant_management.service;

import hust.project.restaurant_management.entity.dto.request.GetStatisticByCustomerRequest;
import hust.project.restaurant_management.entity.dto.request.GetStatisticByMenuItemRequest;
import hust.project.restaurant_management.entity.dto.request.GetStatisticByRevenueRequest;
import hust.project.restaurant_management.entity.dto.response.*;

public interface IStatisticService {
    StatisticByMenuItemResponse getStatisticByMenuItem(GetStatisticByMenuItemRequest request);

    StatisticByCustomerAndDateResponse getStatisticByCustomerAndDate(GetStatisticByCustomerRequest request);

    StatisticByRevenueAndDateResponse getStatisticByRevenueAndDate(GetStatisticByRevenueRequest request);

    StatisticByRevenueAndHourResponse getStatisticByRevenueAndHour(GetStatisticByRevenueRequest request);

    StatisticByCustomerAndHourResponse getStatisticByCustomerAndHour(GetStatisticByCustomerRequest request);
}
