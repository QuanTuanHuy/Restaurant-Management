package hust.project.restaurant_management.repository;

import hust.project.restaurant_management.model.OrderItemKitchenModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderItemKitchenRepository extends IBaseRepository<OrderItemKitchenModel> {
    List<OrderItemKitchenModel> findByStatusOrderByReceivedTimeDesc(String status);

    List<OrderItemKitchenModel> findByIdIn(List<Long> ids);

    void deleteByIdIn(List<Long> ids);
}
