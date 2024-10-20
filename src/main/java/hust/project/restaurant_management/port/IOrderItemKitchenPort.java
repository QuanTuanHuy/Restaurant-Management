package hust.project.restaurant_management.port;

import hust.project.restaurant_management.entity.OrderItemKitchenEntity;
import hust.project.restaurant_management.entity.dto.request.GetOrderItemKitchenRequest;

import java.util.List;

public interface IOrderItemKitchenPort {
    OrderItemKitchenEntity save(OrderItemKitchenEntity orderItemKitchenEntity);

    List<OrderItemKitchenEntity> saveAll(List<OrderItemKitchenEntity> orderItemKitchenEntities);

    List<OrderItemKitchenEntity> getAllOrderItemKitchens(GetOrderItemKitchenRequest filter);

    List<OrderItemKitchenEntity> getOrderItemKitchensByIds(List<Long> ids);

    List<OrderItemKitchenEntity> getOrderItemKitchensByStatus(String status);
}
