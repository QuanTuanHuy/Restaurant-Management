package hust.project.restaurant_management.repository.adapter;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.entity.OrderItemEntity;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.mapper.IOrderItemMapper;
import hust.project.restaurant_management.model.OrderItemModel;
import hust.project.restaurant_management.port.IOrderItemPort;
import hust.project.restaurant_management.repository.IOrderItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderItemAdapter implements IOrderItemPort {
    private final IOrderItemRepository orderItemRepository;
    private final IOrderItemMapper orderItemMapper;

    @Override
    public OrderItemEntity save(OrderItemEntity orderItemEntity) {
        try {
            OrderItemModel orderItemModel = orderItemMapper.toModelFromEntity(orderItemEntity);
            return orderItemMapper.toEntityFromModel(orderItemRepository.save(orderItemModel));
        } catch (Exception e) {
            log.error("[OrderItemAdapter] save order item failed: error: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_ORDER_ITEM_FAILED);
        }
    }

    @Override
    public List<OrderItemEntity> saveAll(List<OrderItemEntity> orderItemEntities) {
        try {
            List<OrderItemModel> orderItemModels = orderItemMapper.toModelsFromEntities(orderItemEntities);
            return orderItemMapper.toEntitiesFromModels(orderItemRepository.saveAll(orderItemModels));
        } catch (Exception e) {
            log.error("[OrderItemAdapter] save all order items failed: error: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_ORDER_ITEM_FAILED);
        }
    }

    @Override
    public List<OrderItemEntity> getOrderItemsByOrderId(Long orderId) {
        return orderItemMapper.toEntitiesFromModels(orderItemRepository.findByOrderId(orderId));
    }

    @Override
    public List<OrderItemEntity> getOrderItemsByIds(List<Long> ids) {
        return orderItemMapper.toEntitiesFromModels(orderItemRepository.findByIdIn(ids));
    }

    @Override
    public OrderItemEntity getOrderItemById(Long id) {
        return orderItemMapper.toEntityFromModel(orderItemRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_ITEM_NOT_FOUND)));
    }

    @Override
    public void deleteOrderItemById(Long id) {
        try {
            orderItemRepository.deleteById(id);
        } catch (Exception e) {
            log.error("[OrderItemAdapter] delete order item failed error: {}", e.getMessage());
            throw new AppException(ErrorCode.DELETE_ORDER_ITEM_FAILED);
        }
    }
}
