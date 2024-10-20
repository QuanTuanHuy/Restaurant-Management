package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.constants.OrderStatusEnum;
import hust.project.restaurant_management.entity.MenuItemEntity;
import hust.project.restaurant_management.entity.OrderEntity;
import hust.project.restaurant_management.entity.OrderItemEntity;
import hust.project.restaurant_management.entity.dto.request.GetStatisticByMenuItemRequest;
import hust.project.restaurant_management.entity.dto.response.MenuItemStatisticResponse;
import hust.project.restaurant_management.entity.dto.response.StatisticByMenuItemResponse;
import hust.project.restaurant_management.port.IMenuItemPort;
import hust.project.restaurant_management.port.IOrderItemPort;
import hust.project.restaurant_management.port.IOrderPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetStatisticByMenuItemUseCase {
    private final IOrderPort orderPort;
    private final IMenuItemPort menuItemPort;
    private final IOrderItemPort orderItemPort;

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
                .menuItemStatistics(menuItemStatistics)
                .build();
    }
}
