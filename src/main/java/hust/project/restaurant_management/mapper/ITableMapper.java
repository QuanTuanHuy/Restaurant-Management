package hust.project.restaurant_management.mapper;

import hust.project.restaurant_management.entity.TableEntity;
import hust.project.restaurant_management.entity.dto.request.CreateTableRequest;
import hust.project.restaurant_management.model.TableModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITableMapper {
    TableModel toModelFromEntity(TableEntity entity);

    TableEntity toEntityFromModel(TableModel model);

    TableEntity toEntityFromRequest(CreateTableRequest request);

    List<TableEntity> toEntitiesFromModels(List<TableModel> models);
}
