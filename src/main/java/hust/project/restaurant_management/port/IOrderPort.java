package hust.project.restaurant_management.port;

import hust.project.restaurant_management.entity.OrderEntity;
import hust.project.restaurant_management.entity.dto.request.GetOrderRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IOrderPort {
    OrderEntity save(OrderEntity orderEntity);

    Pair<PageInfo, List<OrderEntity>> getAllOrders(GetOrderRequest filter);

    List<OrderEntity> getOrdersByIds(List<Long> ids);

    OrderEntity getOrderById(Long id);

    void deleteOrderById(Long id);
}
