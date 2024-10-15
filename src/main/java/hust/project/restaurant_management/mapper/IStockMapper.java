package hust.project.restaurant_management.mapper;

import hust.project.restaurant_management.entity.StockEntity;
import hust.project.restaurant_management.model.StockModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IStockMapper {
    StockEntity toEntityFromModel(StockModel model);

    StockModel toModelFromEntity(StockEntity entity);

    List<StockEntity> toEntitiesFromModels(List<StockModel> models);

    List<StockModel> toModelsFromEntities(List<StockEntity> entities);
}
