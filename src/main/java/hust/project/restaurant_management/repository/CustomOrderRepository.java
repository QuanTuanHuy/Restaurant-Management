package hust.project.restaurant_management.repository;

import hust.project.restaurant_management.entity.OrderEntity;
import hust.project.restaurant_management.entity.dto.request.GetOrderRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface CustomOrderRepository {
    Pair<PageInfo, List<OrderEntity>> getAllOrders(GetOrderRequest filter);
}
