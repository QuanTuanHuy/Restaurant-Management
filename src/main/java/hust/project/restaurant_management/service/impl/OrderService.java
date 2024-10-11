package hust.project.restaurant_management.service.impl;

import hust.project.restaurant_management.entity.OrderEntity;
import hust.project.restaurant_management.entity.dto.request.*;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.service.IOrderService;
import hust.project.restaurant_management.usercase.CreateOrderUseCase;
import hust.project.restaurant_management.usercase.GetOrderUseCase;
import hust.project.restaurant_management.usercase.UpdateOrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;
    private final UpdateOrderUseCase updateOrderUseCase;


    @Override
    public OrderEntity createOrder(CreateOrderRequest request) {
        return createOrderUseCase.createOrder(request);
    }

    @Override
    public Pair<PageInfo, List<OrderEntity>> getAllOrders(GetOrderRequest filter) {
        return getOrderUseCase.getAllOrders(filter);
    }

    @Override
    public OrderEntity updateOrder(Long id, UpdateOrderRequest request) {
        return updateOrderUseCase.updateOrder(id, request);
    }

    @Override
    public OrderEntity getDetailOrder(Long id) {
        return getOrderUseCase.getDetailOrder(id);
    }

    @Override
    public void updateOrderStatus(Long id, UpdateOrderStatusRequest request) {
        updateOrderUseCase.updateOrderStatus(id, request);
    }

    @Override
    public void addMenuItemsToOrder(Long orderId, AddMenuItemsToOrderRequest request) {
        updateOrderUseCase.addMenuItemsToOrder(orderId, request);
    }
}
