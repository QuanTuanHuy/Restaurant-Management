package hust.project.restaurant_management.service;

import hust.project.restaurant_management.entity.OrderItemKitchenEntity;
import hust.project.restaurant_management.entity.dto.request.AddMenuItemsToOrderRequest;
import hust.project.restaurant_management.entity.dto.request.GetOrderItemKitchenRequest;
import hust.project.restaurant_management.entity.dto.request.UpdateOrderItemKitchenStatusRequest;

import java.util.List;

public interface IOrderItemKitchenService {
    void createOrderItemKitchens(Long orderId, AddMenuItemsToOrderRequest request);

    void updateOrderItemKitchenStatus(UpdateOrderItemKitchenStatusRequest request);

    List<OrderItemKitchenEntity> getAllOrderItemKitchens(GetOrderItemKitchenRequest filter);
}
