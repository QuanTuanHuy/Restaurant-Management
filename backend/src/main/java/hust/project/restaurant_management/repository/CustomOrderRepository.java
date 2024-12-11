package hust.project.restaurant_management.repository;

import hust.project.restaurant_management.entity.dto.request.GetOrderRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.model.OrderModel;
import org.springframework.data.util.Pair;

import java.util.List;

public interface CustomOrderRepository {
    Pair<PageInfo, List<OrderModel>> getAllOrders(GetOrderRequest filter);
}
