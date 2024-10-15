package hust.project.restaurant_management.mapper;

import hust.project.restaurant_management.entity.StockHistoryItemEntity;
import hust.project.restaurant_management.entity.dto.request.CreateStockHistoryItemRequest;
import hust.project.restaurant_management.model.StockHistoryItemModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IStockHistoryItemMapper {
    StockHistoryItemEntity toEntityFromModel(StockHistoryItemModel model);

    StockHistoryItemModel toModelFromEntity(StockHistoryItemEntity entity);

    StockHistoryItemEntity toEntityFromRequest(CreateStockHistoryItemRequest request);

    List<StockHistoryItemEntity> toEntitiesFromModels(List<StockHistoryItemModel> models);

    List<StockHistoryItemModel> toModelsFromEntities(List<StockHistoryItemEntity> entities);
}
