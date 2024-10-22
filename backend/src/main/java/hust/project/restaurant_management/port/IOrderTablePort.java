package hust.project.restaurant_management.port;

import hust.project.restaurant_management.entity.OrderTableEntity;
import hust.project.restaurant_management.entity.dto.request.GetOrderTableRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IOrderTablePort {
    OrderTableEntity save(OrderTableEntity orderTableEntity);

    List<OrderTableEntity> saveAll(List<OrderTableEntity> orderTableEntities);

    List<OrderTableEntity> getOrderTablesByOrderId(Long orderId);

    List<OrderTableEntity> getOrderTablesByTableId(Long tableId);

    List<OrderTableEntity> getOrderTablesByOrderIds(List<Long> orderIds);

    Pair<PageInfo, List<OrderTableEntity>> getAllOrderTables(GetOrderTableRequest filter);

    void deleteOrderTableByIds(List<Long> ids);
}
