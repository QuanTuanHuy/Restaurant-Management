package hust.project.restaurant_management.mapper;

import hust.project.restaurant_management.entity.OrderTableEntity;
import hust.project.restaurant_management.model.OrderTableModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IOrderTableMapper {
    OrderTableModel toModelFromEntity(OrderTableEntity entity);

    OrderTableEntity toEntityFromModel(OrderTableModel model);

    List<OrderTableModel> toModelsFromEntities(List<OrderTableEntity> entities);

    List<OrderTableEntity> toEntitiesFromModels(List<OrderTableModel> models);
}
