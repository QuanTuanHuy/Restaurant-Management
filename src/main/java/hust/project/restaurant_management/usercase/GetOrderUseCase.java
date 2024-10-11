package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.entity.*;
import hust.project.restaurant_management.entity.dto.request.GetOrderRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.port.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetOrderUseCase {
    private final IOrderPort orderPort;
    private final ITablePort tablePort;
    private final IOrderTablePort orderTablePort;
    private final IOrderItemPort orderItemPort;
    private final ICustomerPort customerPort;
    private final IMenuItemPort menuItemPort;

    public OrderEntity getDetailOrder(Long id) {
        OrderEntity order = orderPort.getOrderById(id);

        CustomerEntity customer = customerPort.getCustomerById(order.getCustomerId());
        order.setCustomer(customer);

        List<OrderItemEntity> orderItems = orderItemPort.getOrderItemsByOrderId(id);
        List<MenuItemEntity> menuItems = menuItemPort.getMenuItemsByIds(
                orderItems.stream().map(OrderItemEntity::getMenuItemId).toList()
        );

        var mapIdToMenuItem = menuItems.stream().collect(Collectors.toMap(MenuItemEntity::getId, Function.identity()));
        orderItems.forEach(orderItem -> orderItem.setMenuItem(mapIdToMenuItem.getOrDefault(orderItem.getMenuItemId(), null)));

        order.setOrderItems(orderItems);

        List<OrderTableEntity> orderTables = orderTablePort.getOrderTablesByOrderId(id);
        List<TableEntity> tables = tablePort.getTablesByIds(
                orderTables.stream().map(OrderTableEntity::getTableId).toList()
        );

        for (var orderTable : orderTables) {
            orderTable.setTable(tables.stream().filter(table -> table.getId().equals(orderTable.getTableId())).findFirst().orElse(null));
        }
        order.setOrderTables(orderTables);

        return order;
    }

    public Pair<PageInfo, List<OrderEntity>> getAllOrders(GetOrderRequest filter) {
        return orderPort.getAllOrders(filter);
    }

}