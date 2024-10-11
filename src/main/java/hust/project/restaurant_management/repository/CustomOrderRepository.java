package hust.project.restaurant_management.repository;

import hust.project.restaurant_management.entity.dto.request.GetOrderRequest;
import hust.project.restaurant_management.model.OrderModel;

import java.util.List;

public interface CustomOrderRepository {
    List<OrderModel> getAllOrders(GetOrderRequest filter);
}
