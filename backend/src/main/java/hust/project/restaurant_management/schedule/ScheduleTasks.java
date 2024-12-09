package hust.project.restaurant_management.schedule;

import hust.project.restaurant_management.constants.OrderStatusEnum;
import hust.project.restaurant_management.entity.OrderEntity;
import hust.project.restaurant_management.port.IOrderPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduleTasks {
    private final IOrderPort orderPort;

    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void updateOrderStatus() {
        List<OrderEntity> orders = orderPort.getOrdersByStatus(OrderStatusEnum.CONFIRMED.name());

        List<OrderEntity> abandonedOrders = orders.stream()
                .filter(order -> order.getCheckInTime()
                        .isBefore(LocalDateTime.now().minus(Duration.ofMinutes(30))))
                .toList();

        abandonedOrders.forEach(order -> {
            order.setOrderStatus(OrderStatusEnum.ABANDONED.name());
            log.info("[ScheduleTasks] order {} is abandoned", order.getId());
            orderPort.save(order);
        });

    }
}
