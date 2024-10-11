package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.constants.OrderItemStatusEnum;
import hust.project.restaurant_management.constants.OrderStatusEnum;
import hust.project.restaurant_management.entity.OrderEntity;
import hust.project.restaurant_management.entity.OrderItemEntity;
import hust.project.restaurant_management.entity.OrderTableEntity;
import hust.project.restaurant_management.entity.TableEntity;
import hust.project.restaurant_management.entity.dto.request.AddMenuItemsToOrderRequest;
import hust.project.restaurant_management.entity.dto.request.GetTableAvailableRequest;
import hust.project.restaurant_management.entity.dto.request.UpdateOrderRequest;
import hust.project.restaurant_management.entity.dto.request.UpdateOrderStatusRequest;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.port.IOrderItemPort;
import hust.project.restaurant_management.port.IOrderPort;
import hust.project.restaurant_management.port.IOrderTablePort;
import hust.project.restaurant_management.port.ITablePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateOrderUseCase {
    private final IOrderPort orderPort;
    private final IOrderTablePort orderTablePort;
    private final ITablePort tablePort;
    private final IOrderItemPort orderItemPort;
//    private final IMenuItemPort menuItemPort;

    @Transactional
    public OrderEntity updateOrder(Long id, UpdateOrderRequest request) {
        OrderEntity order = orderPort.getOrderById(id);

        if (!order.getOrderStatus().equals(OrderStatusEnum.CONFIRMED.name()) &&
                !order.getOrderStatus().equals(OrderStatusEnum.CHECKED_IN.name())) {
            log.error("[UpdateOrderUseCase] cannot update order with status: {}", order.getOrderStatus());
            throw new AppException(ErrorCode.UPDATE_ORDER_FAILED);
        }

        if (order.getOrderStatus().equals(OrderStatusEnum.CONFIRMED.name())) {
            order.setCheckInTime(request.getCheckInTime());
        }

        order.setCheckOutTime(request.getCheckOutTime());
        order.setNote(request.getNote());
        order.setNumberOfPeople(request.getNumberOfPeople());
        order.setUserId(request.getUserId());

        List<Long> oldOrderTableIds = orderTablePort.getOrderTablesByOrderId(id)
                .stream()
                .map(OrderTableEntity::getTableId)
                .toList();

        orderTablePort.deleteOrderTableByIds(oldOrderTableIds);


        HashSet<Long> availableTableIds = (HashSet<Long>)
                tablePort.getAllTablesAvailable(GetTableAvailableRequest.builder()
                            .checkInTime(request.getCheckInTime())
                            .checkOutTime(request.getCheckOutTime())
                            .build())
                    .stream()
                    .map(TableEntity::getId)
                    .collect(Collectors.toSet());

        if (!request.getTableIds().isEmpty()) {
            if (!availableTableIds.containsAll(request.getTableIds())) {
                log.error("[UpdateOrderUseCase] Not all tables are available");
                throw new AppException(ErrorCode.UPDATE_ORDER_FAILED);
            }

            List<OrderTableEntity> newOrderTables = request.getTableIds()
                    .stream()
                    .map(tableId -> OrderTableEntity.builder()
                            .orderId(id)
                            .tableId(tableId)
                            .build())
                    .toList();
            order.setOrderTables(orderTablePort.saveAll(newOrderTables));
        }

        return orderPort.save(order);

    }

    @Transactional
    public void addMenuItemsToOrder(Long orderId, AddMenuItemsToOrderRequest request) {
        orderPort.getOrderById(orderId);

        List<OrderItemEntity> orderItems = orderItemPort.getOrderItemsByOrderId(orderId);

        HashMap<Long, OrderItemEntity> mapMenuItemIdToOrderItem = (HashMap<Long, OrderItemEntity>) orderItems
                .stream()
                .collect(Collectors.toMap(OrderItemEntity::getMenuItemId, Function.identity()));

        List<OrderItemEntity> modifiedOrderItems = new ArrayList<>();

//        List<MenuItemEntity> menuItems = menuItemPort.getMenuItemsByIds(
//                request.getMenuItemsQuantity().stream().map(MenuItemQuantityRequest::getMenuItemId).toList()
//        );
//        Map<Long, MenuItemEntity> mapIdToMenuItem = menuItems.stream()
//                        .collect(Collectors.toMap(MenuItemEntity::getId, Function.identity()));

        request.getMenuItemsQuantity().forEach(menuItemQuantity -> {
            Long menuItemId = menuItemQuantity.getMenuItemId();
            Long quantity = menuItemQuantity.getQuantity();
            if (quantity <= 0) {
                log.error("[UpdateOrderUseCase] addMenuItemsToOrder error: Invalid quantity");
                throw new AppException(ErrorCode.MENU_ITEM_QUANTITY_INVALID);
            }

            if (mapMenuItemIdToOrderItem.containsKey(menuItemId)) {
                OrderItemEntity orderItem = mapMenuItemIdToOrderItem.get(menuItemId);
                orderItem.setOrderedQuantity(orderItem.getOrderedQuantity() + quantity);
                orderItem.setStatus(OrderItemStatusEnum.IN_PROGRESS.name());
                modifiedOrderItems.add(orderItem);
            } else {
                OrderItemEntity newOrderItem = OrderItemEntity.builder()
                        .orderId(orderId)
                        .orderedQuantity(quantity)
                        .reservedQuantity(0L)
                        .menuItemId(menuItemId)
                        .note(menuItemQuantity.getNote())
                        .status(OrderItemStatusEnum.IN_PROGRESS.name())
                        .build();
                modifiedOrderItems.add(newOrderItem);
            }
        });

        orderItemPort.saveAll(modifiedOrderItems);
    }

    @Transactional
    public void updateOrderStatus(Long id, UpdateOrderStatusRequest request) {
        // TODO
        // delete order table if order status is abandoned

        OrderEntity order = orderPort.getOrderById(id);
        validateOrderStatus(order.getOrderStatus(), OrderStatusEnum.valueOf(request.getStatus()).name());

        order.setOrderStatus(request.getStatus());
        orderPort.save(order);
    }

    void validateOrderStatus(String oldStatus, String newStatus) {
        if ((oldStatus.equals(OrderStatusEnum.CONFIRMED.name()) && newStatus.equals(OrderStatusEnum.CHECKED_IN.name()))
        || (oldStatus.equals(OrderStatusEnum.CONFIRMED.name()) && newStatus.equals(OrderStatusEnum.ABANDONED.name()))
        || (oldStatus.equals(OrderStatusEnum.CONFIRMED.name()) && newStatus.equals(OrderStatusEnum.CANCELLED.name()))
        || (oldStatus.equals(OrderStatusEnum.CHECKED_IN.name()) && newStatus.equals(OrderStatusEnum.COMPLETED.name()))) {
            return;
        }
        log.error("[UpdateOrderUseCase] validateOrderStatus: Invalid order status transition");
        throw new AppException(ErrorCode.UPDATE_ORDER_STATUS_FAILED);
    }

}
