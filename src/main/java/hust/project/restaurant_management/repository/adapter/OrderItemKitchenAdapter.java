package hust.project.restaurant_management.repository.adapter;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.constants.OrderItemKitchenStatusEnum;
import hust.project.restaurant_management.entity.OrderItemKitchenEntity;
import hust.project.restaurant_management.entity.dto.request.GetOrderItemKitchenRequest;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.mapper.IOrderItemKitchenMapper;
import hust.project.restaurant_management.model.OrderItemKitchenModel;
import hust.project.restaurant_management.port.IOrderItemKitchenPort;
import hust.project.restaurant_management.repository.IOrderItemKitchenRepository;
import hust.project.restaurant_management.repository.specification.OrderItemKitchenSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderItemKitchenAdapter implements IOrderItemKitchenPort {
    private final IOrderItemKitchenRepository orderItemKitchenRepository;
    private final IOrderItemKitchenMapper orderItemKitchenMapper;

    @Override
    public OrderItemKitchenEntity save(OrderItemKitchenEntity orderItemKitchenEntity) {
        try {
            OrderItemKitchenModel orderItemKitchenModel = orderItemKitchenMapper.toModelFromEntity(orderItemKitchenEntity);
            return orderItemKitchenMapper.toEntityFromModel(orderItemKitchenRepository.save(orderItemKitchenModel));
        } catch (Exception e) {
            log.error("[OrderItemKitchenAdapter] save order item kitchen failed: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_ORDER_ITEM_KITCHEN_FAILED);
        }
    }

    @Override
    public List<OrderItemKitchenEntity> saveAll(List<OrderItemKitchenEntity> orderItemKitchenEntities) {
        try {
            List<OrderItemKitchenModel> orderItemKitchenModels = orderItemKitchenMapper.toModelsFromEntities(orderItemKitchenEntities);
            return orderItemKitchenMapper.toEntitiesFromModels(orderItemKitchenRepository.saveAll(orderItemKitchenModels));
        } catch (Exception e) {
            log.error("[OrderItemKitchenAdapter] save all order item kitchens failed: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_ORDER_ITEM_KITCHEN_FAILED);
        }
    }

    @Override
    public List<OrderItemKitchenEntity> getAllOrderItemKitchens(GetOrderItemKitchenRequest filter) {
        Sort sort = Sort.by("receivedTime").descending();
        return orderItemKitchenMapper.toEntitiesFromModels(
                orderItemKitchenRepository.findAll(OrderItemKitchenSpecification.getAll(filter), sort)
        );
    }

    @Override
    public List<OrderItemKitchenEntity> getOrderItemKitchensByIds(List<Long> ids) {
        return orderItemKitchenMapper.toEntitiesFromModels(orderItemKitchenRepository.findByIdIn(ids));
    }

    @Override
    public List<OrderItemKitchenEntity> getOrderItemKitchensByStatus(String status) {
        return orderItemKitchenMapper.toEntitiesFromModels(orderItemKitchenRepository
                .findByStatusOrderByReceivedTimeDesc(OrderItemKitchenStatusEnum.valueOf(status).name()));
    }
}
