package hust.project.restaurant_management.mapper;

import hust.project.restaurant_management.entity.StockHistoryEntity;
import hust.project.restaurant_management.entity.dto.request.CreateStockHistoryRequest;
import hust.project.restaurant_management.model.StockHistoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IStockHistoryMapper {
    StockHistoryModel toModelFromEntity(StockHistoryEntity entity);

    StockHistoryEntity toEntityFromModel(StockHistoryModel model);

    @Mapping(target = "stockHistoryItems", ignore = true)
    @Mapping(target = "status", ignore = true)
    StockHistoryEntity toEntityFromRequest(CreateStockHistoryRequest request);

    List<StockHistoryEntity> toEntitiesFromModels(List<StockHistoryModel> models);


}
