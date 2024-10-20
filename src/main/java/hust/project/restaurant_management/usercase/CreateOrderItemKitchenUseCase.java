package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.constants.OrderItemKitchenStatusEnum;
import hust.project.restaurant_management.entity.MenuItemEntity;
import hust.project.restaurant_management.entity.OrderItemKitchenEntity;
import hust.project.restaurant_management.entity.dto.request.AddMenuItemsToOrderRequest;
import hust.project.restaurant_management.entity.dto.request.MenuItemQuantityRequest;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.port.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateOrderItemKitchenUseCase {
    private final IOrderItemKitchenPort orderItemKitchenPort;
    private final IOrderPort orderPort;
    private final ITablePort tablePort;
    private final IOrderTablePort orderTablePort;
    private final IMenuItemPort menuItemPort;

    @Transactional
    public void createOrderItemKitchens(Long orderId, AddMenuItemsToOrderRequest request) {
        orderPort.getOrderById(orderId);

        var table = tablePort.getTableById(orderTablePort.getOrderTablesByOrderId(orderId).get(0).getTableId());

        List<Long> menuItemIds = request.getMenuItemsQuantity().stream()
                .map(MenuItemQuantityRequest::getMenuItemId)
                .toList();

        List<MenuItemEntity> menuItems = menuItemPort.getMenuItemsByIds(menuItemIds);

        if (menuItems.size() != menuItemIds.size()) {
            log.error("[CreateOrderItemKitchenUseCase] menu items not found");
            throw new AppException(ErrorCode.CREATE_MENU_ITEM_FAIL);
        }


        List<OrderItemKitchenEntity> orderItemKitchens = request.getMenuItemsQuantity().stream()
                .map(menuItemQuantity -> OrderItemKitchenEntity.builder()
                        .orderId(orderId)
                        .tableId(table.getId())
                        .menuItemId(menuItemQuantity.getMenuItemId())
                        .quantity(menuItemQuantity.getQuantity())
                        .note(menuItemQuantity.getNote())
                        .status(OrderItemKitchenStatusEnum.PENDING.name())
                        .receivedTime(LocalDateTime.now())
                        .build())
                .toList();

        orderItemKitchenPort.saveAll(orderItemKitchens);
    }
}
