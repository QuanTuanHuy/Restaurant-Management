package hust.project.restaurant_management.service;

import hust.project.restaurant_management.entity.OrderEntity;
import hust.project.restaurant_management.entity.dto.request.*;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IOrderService {
    OrderEntity createOrder(CreateOrderRequest request);

    Pair<PageInfo, List<OrderEntity>> getAllOrders(GetOrderRequest filter);

    OrderEntity getDetailOrder(Long id);

    void updateOrderStatus(Long id, UpdateOrderStatusRequest request);

    void addMenuItemsToOrder(Long orderId, AddMenuItemsToOrderRequest request);

    OrderEntity updateOrder(Long id, UpdateOrderRequest request);
}
