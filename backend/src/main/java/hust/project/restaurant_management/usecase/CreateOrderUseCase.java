package hust.project.restaurant_management.usecase;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.constants.OrderStatusEnum;
import hust.project.restaurant_management.entity.OrderEntity;
import hust.project.restaurant_management.entity.OrderTableEntity;
import hust.project.restaurant_management.entity.TableEntity;
import hust.project.restaurant_management.entity.dto.request.CreateOrderRequest;
import hust.project.restaurant_management.entity.dto.request.GetTableAvailableRequest;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.mapper.IOrderMapper;
import hust.project.restaurant_management.port.ICustomerPort;
import hust.project.restaurant_management.port.IOrderPort;
import hust.project.restaurant_management.port.IOrderTablePort;
import hust.project.restaurant_management.port.ITablePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateOrderUseCase {
    private final IOrderPort orderPort;
    private final ICustomerPort customerPort;
    private final IOrderTablePort orderTablePort;
    private final ITablePort tablePort;
    private final IOrderMapper orderMapper;

    @Transactional
    public OrderEntity createOrder(CreateOrderRequest request) {

        if (request.getCheckInTime().isAfter(request.getCheckOutTime()) ||
                request.getCheckInTime().isBefore(LocalDateTime.now())) {
            log.error("[CreateOrderUseCase] invalid time range for order");
            throw new AppException(ErrorCode.CREATE_ORDER_FAILED);
        }

        customerPort.getCustomerById(request.getCustomerId());

        OrderEntity order = orderMapper.toEntityFromRequest(request);

        HashSet<Long> availableTableIds = (HashSet<Long>)
                tablePort.getAllTablesAvailable(new GetTableAvailableRequest(
                        request.getCheckInTime(), request.getCheckOutTime()))
                .stream()
                .map(TableEntity::getId)
                .collect(Collectors.toSet());

        if (!availableTableIds.containsAll(request.getTableIds())) {
            log.error("[CreateOrderUseCase] createOrder: Not all tables are available");
            throw new AppException(ErrorCode.CREATE_ORDER_FAILED);
        }

        order.setOrderStatus(OrderStatusEnum.CONFIRMED.name());
        order = orderPort.save(order);
        final Long orderId = order.getId();

        List<OrderTableEntity> orderTableEntities = request.getTableIds().stream()
                .map(tableId -> OrderTableEntity.builder()
                        .orderId(orderId)
                        .tableId(tableId)
                        .build())
                .toList();

        List<OrderTableEntity> savedOrderTable = orderTablePort.saveAll(orderTableEntities);
        order.setOrderTables(savedOrderTable);

        return order;
    }

}
