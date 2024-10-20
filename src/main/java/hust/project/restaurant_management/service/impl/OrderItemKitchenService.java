package hust.project.restaurant_management.service.impl;

import hust.project.restaurant_management.entity.OrderItemKitchenEntity;
import hust.project.restaurant_management.entity.dto.request.AddMenuItemsToOrderRequest;
import hust.project.restaurant_management.entity.dto.request.GetOrderItemKitchenRequest;
import hust.project.restaurant_management.entity.dto.request.UpdateOrderItemKitchenStatusRequest;
import hust.project.restaurant_management.service.IOrderItemKitchenService;
import hust.project.restaurant_management.usercase.CreateOrderItemKitchenUseCase;
import hust.project.restaurant_management.usercase.GetOrderItemKitchenUseCase;
import hust.project.restaurant_management.usercase.UpdateOrderItemKitchenUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemKitchenService implements IOrderItemKitchenService {
    private final CreateOrderItemKitchenUseCase createOrderItemKitchenUseCase;
    private final UpdateOrderItemKitchenUseCase updateOrderItemKitchenUseCase;
    private final GetOrderItemKitchenUseCase getOrderItemKitchenUseCase;

    @Override
    public void createOrderItemKitchens(Long orderId, AddMenuItemsToOrderRequest request) {
        createOrderItemKitchenUseCase.createOrderItemKitchens(orderId, request);
    }

    @Override
    public void updateOrderItemKitchenStatus(UpdateOrderItemKitchenStatusRequest request) {
        updateOrderItemKitchenUseCase.updateOrderItemKitchenStatus(request);
    }

    @Override
    public List<OrderItemKitchenEntity> getAllOrderItemKitchens(GetOrderItemKitchenRequest filter) {
        return getOrderItemKitchenUseCase.getAllOrderItemKitchens(filter);
    }
}
