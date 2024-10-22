package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.constants.OrderStatusEnum;
import hust.project.restaurant_management.entity.MenuItemEntity;
import hust.project.restaurant_management.entity.OrderEntity;
import hust.project.restaurant_management.entity.OrderItemEntity;
import hust.project.restaurant_management.entity.dto.request.GetStatisticByCustomerRequest;
import hust.project.restaurant_management.entity.dto.request.GetStatisticByMenuItemRequest;
import hust.project.restaurant_management.entity.dto.request.GetStatisticByRevenueRequest;
import hust.project.restaurant_management.entity.dto.response.*;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.port.IMenuItemPort;
import hust.project.restaurant_management.port.IOrderItemPort;
import hust.project.restaurant_management.port.IOrderPort;
import hust.project.restaurant_management.repository.CustomStatisticRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetStatisticUseCase {
    private final IOrderPort orderPort;
    private final IMenuItemPort menuItemPort;
    private final IOrderItemPort orderItemPort;

    private final CustomStatisticRepository customStatisticRepository;

    public StatisticByMenuItemResponse getStatisticByMenuItem(GetStatisticByMenuItemRequest request) {
        List<OrderEntity> orders = orderPort.getOrdersInTimeRangeAndStatus(request.getStartTime(), request.getEndTime(),
                OrderStatusEnum.COMPLETED.name());

        if (orders.isEmpty()) {
            return StatisticByMenuItemResponse.builder().build();
        }

        List<OrderItemEntity> orderItems = orderItemPort.getOrderItemsByOrderIds(
                orders.stream().map(OrderEntity::getId).toList()
        );

        List<Long> menuItemIds = orderItems.stream()
                .map(OrderItemEntity::getMenuItemId).toList();
        List<MenuItemEntity> menuItems = menuItemPort.getMenuItemsByIds(menuItemIds);
        var mapIdToMenuItem = menuItems.stream()
                .collect(Collectors.toMap(MenuItemEntity::getId, Function.identity()));


        List<MenuItemStatisticResponse> menuItemStatistics = new ArrayList<>(orderItems.stream()
                .collect(Collectors.groupingBy(OrderItemEntity::getMenuItemId))
                .entrySet().stream()
                .map(entry -> {
                    MenuItemEntity menuItem = mapIdToMenuItem.get(entry.getKey());
                    return MenuItemStatisticResponse.builder()
                            .menuItemId(menuItem.getId())
                            .menuItemName(menuItem.getTitle())
                            .quantity(entry.getValue().stream().mapToLong(OrderItemEntity::getOrderedQuantity).sum())
                            .revenue(entry.getValue().stream().mapToDouble(OrderItemEntity::getPrice).sum())
                            .build();
                }).toList());

        menuItemStatistics.sort((m1, m2) -> {
            if (request.getCategory().equals("QUANTITY")) {
                return Long.compare(m2.getQuantity(), m1.getQuantity());
            } else if (request.getCategory().equals("REVENUE")) {
                return Double.compare(m2.getRevenue(), m1.getRevenue());
            }
            return 0;
        });


        return StatisticByMenuItemResponse.builder()
                .menuItemStatistics(menuItemStatistics.subList(0, Math.min(request.getLimit(), menuItemStatistics.size())))
                .build();
    }

    public StatisticByCustomerAndDateResponse getStatisticByCustomer(GetStatisticByCustomerRequest request) {
        validateTimeRange(request.getStartDate(), request.getEndDate());

        var customerStatistics = customStatisticRepository.getStatisticByCustomer(request);

        Long totalCustomer = customerStatistics.stream().mapToLong(CustomerStatisticPerDateResponse::getCount).sum();

        return StatisticByCustomerAndDateResponse.builder()
                .totalCustomer(totalCustomer)
                .customerStatistics(customerStatistics)
                .build();
    }

    public StatisticByRevenueAndDateResponse getStatisticByRevenue(GetStatisticByRevenueRequest request) {
        validateTimeRange(request.getStartDate(), request.getEndDate());

        var revenueStatistics = customStatisticRepository.getStatisticByRevenue(request);

        Double totalRevenue = revenueStatistics.stream().mapToDouble(RevenueStatisticPerDateResponse::getRevenue).sum();

        return StatisticByRevenueAndDateResponse.builder()
                .totalRevenue(totalRevenue)
                .revenueStatistics(revenueStatistics)
                .build();
    }

    public StatisticByRevenueAndHourResponse getStatisticByRevenueAndHour(GetStatisticByRevenueRequest request) {
        validateTimeRange(request.getStartDate(), request.getEndDate());

        var revenueStatistics = customStatisticRepository.getStatisticByRevenueAndHour(request);

        Double totalRevenue = revenueStatistics.stream().mapToDouble(RevenueStatisticPerHourResponse::getRevenue).sum();

        return StatisticByRevenueAndHourResponse.builder()
                .totalRevenue(totalRevenue)
                .revenueStatistics(revenueStatistics)
                .build();

    }

    public StatisticByCustomerAndHourResponse getStatisticByCustomerAndHour(GetStatisticByCustomerRequest request) {
        validateTimeRange(request.getStartDate(), request.getEndDate());

        var customerStatistics = customStatisticRepository.getStatisticByCustomerAndHour(request);

        Integer totalCustomer = customerStatistics.stream().mapToInt(CustomerStatisticPerHourResponse::getCount).sum();

        return StatisticByCustomerAndHourResponse.builder()
                .totalCustomer(totalCustomer)
                .customerStatistics(customerStatistics)
                .build();
    }

    private void validateTimeRange(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            log.error("[GetStatisticUseCase] Time range invalid");
            throw new AppException(ErrorCode.TIME_INVALID);
        }
    }
}
