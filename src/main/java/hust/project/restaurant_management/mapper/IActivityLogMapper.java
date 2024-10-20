package hust.project.restaurant_management.mapper;

import hust.project.restaurant_management.entity.ActivityLogEntity;
import hust.project.restaurant_management.model.ActivityLogModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IActivityLogMapper {
    ActivityLogEntity toEntityFromModel(ActivityLogModel model);

    ActivityLogModel toModelFromEntity(ActivityLogEntity entity);

    List<ActivityLogEntity> toEntitiesFromModels(List<ActivityLogModel> models);
}
