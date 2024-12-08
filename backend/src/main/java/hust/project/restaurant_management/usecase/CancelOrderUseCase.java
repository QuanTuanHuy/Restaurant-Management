package hust.project.restaurant_management.usecase;

import hust.project.restaurant_management.constants.OrderStatusEnum;
import hust.project.restaurant_management.entity.OrderEntity;
import hust.project.restaurant_management.entity.OrderTableEntity;
import hust.project.restaurant_management.port.IOrderPort;
import hust.project.restaurant_management.port.IOrderTablePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CancelOrderUseCase {
    private final IOrderPort orderPort;
    private final IOrderTablePort orderTablePort;

    public void cancelOrder(Long id) {
        OrderEntity order = orderPort.getOrderById(id);

        order.setOrderStatus(OrderStatusEnum.CANCELLED.name());

        List<Long> orderTableIds = orderTablePort.getOrderTablesByOrderId(id).stream()
                .map(OrderTableEntity::getId).toList();

        orderTablePort.deleteOrderTableByIds(orderTableIds);

        orderPort.save(order);
    }
}
