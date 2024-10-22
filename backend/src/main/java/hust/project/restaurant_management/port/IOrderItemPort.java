package hust.project.restaurant_management.port;

import hust.project.restaurant_management.entity.OrderItemEntity;

import java.util.List;

public interface IOrderItemPort {
    OrderItemEntity save(OrderItemEntity orderItemEntity);

    List<OrderItemEntity> saveAll(List<OrderItemEntity> orderItemEntities);

    List<OrderItemEntity> getOrderItemsByOrderId(Long orderId);

    OrderItemEntity getOrderItemsByOrderIdAndMenuItemId(Long orderId, Long menuItemId);

    List<OrderItemEntity> getOrderItemsByOrderIds(List<Long> orderIds);

    List<OrderItemEntity> getOrderItemsByIds(List<Long> ids);

    OrderItemEntity getOrderItemById(Long id);

    void deleteOrderItemById(Long id);
}
