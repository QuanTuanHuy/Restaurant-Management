package hust.project.restaurant_management.mapper;

import hust.project.restaurant_management.entity.WorkAttendanceEntity;
import hust.project.restaurant_management.model.WorkAttendanceModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IWorkAttendanceMapper {
    WorkAttendanceEntity toEntityFromModel(WorkAttendanceModel model);

    WorkAttendanceModel toModelFromEntity(WorkAttendanceEntity entity);

    List<WorkAttendanceModel> toModelsFromEntities(List<WorkAttendanceEntity> entities);

    List<WorkAttendanceEntity> toEntitiesFromModels(List<WorkAttendanceModel> models);
}
