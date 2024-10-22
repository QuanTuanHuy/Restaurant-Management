package hust.project.restaurant_management.mapper;

import hust.project.restaurant_management.entity.OrderEntity;
import hust.project.restaurant_management.entity.dto.request.CreateOrderRequest;
import hust.project.restaurant_management.model.OrderModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IOrderMapper {
    OrderModel toModelFromEntity(OrderEntity entity);

    OrderEntity toEntityFromModel(OrderModel model);

    OrderEntity toEntityFromRequest(CreateOrderRequest request);

    List<OrderEntity> toEntitiesFromModels(List<OrderModel> models);
}
