package hust.project.restaurant_management.mapper;

import hust.project.restaurant_management.entity.OrderItemKitchenEntity;
import hust.project.restaurant_management.model.OrderItemKitchenModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IOrderItemKitchenMapper {
    OrderItemKitchenEntity toEntityFromModel(OrderItemKitchenModel model);

    OrderItemKitchenModel toModelFromEntity(OrderItemKitchenEntity entity);

    List<OrderItemKitchenModel> toModelsFromEntities(List<OrderItemKitchenEntity> entities);

    List<OrderItemKitchenEntity> toEntitiesFromModels(List<OrderItemKitchenModel> models);
}
