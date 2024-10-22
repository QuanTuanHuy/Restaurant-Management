package hust.project.restaurant_management.repository;

import hust.project.restaurant_management.model.OrderModel;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IOrderRepository extends IBaseRepository<OrderModel>, CustomOrderRepository {
    List<OrderModel> findByIdIn(List<Long> ids);

    List<OrderModel> findByCheckInTimeBetweenAndOrderStatusIs(LocalDateTime startTime,LocalDateTime endTime,
                                                              String status);
}
