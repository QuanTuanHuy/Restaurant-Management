package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.entity.MenuItemEntity;
import hust.project.restaurant_management.entity.OrderItemKitchenEntity;
import hust.project.restaurant_management.entity.TableEntity;
import hust.project.restaurant_management.entity.dto.request.GetOrderItemKitchenRequest;
import hust.project.restaurant_management.port.IMenuItemPort;
import hust.project.restaurant_management.port.IOrderItemKitchenPort;
import hust.project.restaurant_management.port.ITablePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetOrderItemKitchenUseCase {
    private final IOrderItemKitchenPort orderItemKitchenPort;
    private final ITablePort tablePort;
    private final IMenuItemPort menuItemPort;

    public List<OrderItemKitchenEntity> getAllOrderItemKitchens(GetOrderItemKitchenRequest filter) {
        List<OrderItemKitchenEntity> orderItemKitchens = orderItemKitchenPort.getAllOrderItemKitchens(filter);

        if (orderItemKitchens.isEmpty()) {
            return List.of();
        }

        List<Long> tableIds = orderItemKitchens.stream()
                .map(OrderItemKitchenEntity::getTableId)
                .distinct().toList();
        var tables = tablePort.getTablesByIds(tableIds);
        var mapIdToTable = tables.stream()
                .collect(Collectors.toMap(TableEntity::getId, Function.identity()));


        List<Long> menuItemIds = orderItemKitchens.stream()
                .map(OrderItemKitchenEntity::getMenuItemId)
                .distinct().toList();
        var menuItems = menuItemPort.getMenuItemsByIds(menuItemIds);
        var mapIdToMenuItem = menuItems.stream()
                .collect(Collectors.toMap(MenuItemEntity::getId, Function.identity()));


        orderItemKitchens.forEach(orderItemKitchen -> {
            orderItemKitchen.setMenuItem(mapIdToMenuItem.getOrDefault(orderItemKitchen.getMenuItemId(), null));
            orderItemKitchen.setTable(mapIdToTable.getOrDefault(orderItemKitchen.getTableId(), null));
        });

        return orderItemKitchens;
    }
}
