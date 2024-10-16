package hust.project.restaurant_management.mapper;

import hust.project.restaurant_management.entity.ShiftEntity;
import hust.project.restaurant_management.entity.dto.request.CreateShiftRequest;
import hust.project.restaurant_management.model.ShiftModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IShiftMapper {
    ShiftEntity toEntityFromModel(ShiftModel model);

    ShiftModel toModelFromEntity(ShiftEntity entity);

    ShiftEntity toEntityFromRequest(CreateShiftRequest request);

    List<ShiftEntity> toEntitiesFromModels(List<ShiftModel> models);
}
