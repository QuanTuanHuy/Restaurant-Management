package hust.project.restaurant_management.repository;

import hust.project.restaurant_management.model.OrderItemModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderItemRepository extends IBaseRepository<OrderItemModel> {
    List<OrderItemModel> findByIdIn(List<Long> ids);

    List<OrderItemModel> findByOrderId(Long orderId);
}
