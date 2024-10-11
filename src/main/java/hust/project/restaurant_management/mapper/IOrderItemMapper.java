package hust.project.restaurant_management.mapper;

import hust.project.restaurant_management.entity.OrderItemEntity;
import hust.project.restaurant_management.model.OrderItemModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IOrderItemMapper {
    OrderItemEntity toEntityFromModel(OrderItemModel model);

    OrderItemModel toModelFromEntity(OrderItemEntity entity);

    List<OrderItemEntity> toEntitiesFromModels(List<OrderItemModel> models);

    List<OrderItemModel> toModelsFromEntities(List<OrderItemEntity> entities);
}
