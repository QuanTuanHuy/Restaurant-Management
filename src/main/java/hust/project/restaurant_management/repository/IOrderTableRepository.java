package hust.project.restaurant_management.repository;

import hust.project.restaurant_management.model.OrderTableModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderTableRepository extends IBaseRepository<OrderTableModel> {
    List<OrderTableModel> findByIdIn(List<Long> ids);

    List<OrderTableModel> findByOrderId(Long orderId);

    List<OrderTableModel> findByTableId(Long tableId);

    void deleteByIdIn(List<Long> ids);
}
