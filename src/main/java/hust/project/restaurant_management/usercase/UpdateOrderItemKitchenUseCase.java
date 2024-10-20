package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.constants.OrderItemKitchenStatusEnum;
import hust.project.restaurant_management.constants.OrderItemStatusEnum;
import hust.project.restaurant_management.entity.OrderItemEntity;
import hust.project.restaurant_management.entity.OrderItemKitchenEntity;
import hust.project.restaurant_management.entity.dto.request.UpdateOrderItemKitchenStatusRequest;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.port.IOrderItemKitchenPort;
import hust.project.restaurant_management.port.IOrderItemPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateOrderItemKitchenUseCase {
    private final IOrderItemKitchenPort orderItemKitchenPort;
    private final IOrderItemPort orderItemPort;

    @Transactional
    public void updateOrderItemKitchenStatus(UpdateOrderItemKitchenStatusRequest request) {
        var orderItemKitchenIds = request.getOrderItemKitchenIds();

        List<OrderItemKitchenEntity> orderItemKitchens = orderItemKitchenPort.getOrderItemKitchensByIds(orderItemKitchenIds);

        if (orderItemKitchens.size() != orderItemKitchenIds.size()) {
            log.error("[UpdateOrderItemKitchenUseCase] order item kitchens not found");
            throw new AppException(ErrorCode.UPDATE_ORDER_ITEM_KITCHEN_FAILED);
        }

        var status = request.getStatus();
        orderItemKitchens.forEach(orderItemKitchen -> validateStatus(orderItemKitchen.getStatus(), status));

        if (status.equals(OrderItemKitchenStatusEnum.READY.name())) {
            orderItemKitchens.forEach(orderItemKitchen -> orderItemKitchen.setStatus(OrderItemKitchenStatusEnum.READY.name()));
        } else if (status.equals(OrderItemKitchenStatusEnum.DELIVERED.name())) {
            orderItemKitchens.forEach(orderItemKitchen -> {
                orderItemKitchen.setStatus(OrderItemKitchenStatusEnum.DELIVERED.name());


                OrderItemEntity orderItem = orderItemPort.getOrderItemsByOrderIdAndMenuItemId(orderItemKitchen.getOrderId(), orderItemKitchen.getMenuItemId());
                orderItem.setReservedQuantity(orderItem.getReservedQuantity() + orderItemKitchen.getQuantity());
                if (orderItem.getOrderedQuantity().equals(orderItem.getReservedQuantity())) {
                    orderItem.setStatus(OrderItemStatusEnum.DONE.name());
                }
                orderItemPort.save(orderItem);
            });
        }

        orderItemKitchenPort.saveAll(orderItemKitchens);

    }

    private void validateStatus(String oldStatus, String newStatus) {
        if ((oldStatus.equals(OrderItemKitchenStatusEnum.PENDING.name()) && newStatus.equals(OrderItemKitchenStatusEnum.READY.name()))
        || (oldStatus.equals(OrderItemKitchenStatusEnum.READY.name()) && newStatus.equals(OrderItemKitchenStatusEnum.DELIVERED.name())))
            return;

        log.error("[UpdateOrderItemKitchenUseCase] invalid status transition");
        throw new AppException(ErrorCode.UPDATE_ORDER_ITEM_KITCHEN_FAILED);
    }
}
