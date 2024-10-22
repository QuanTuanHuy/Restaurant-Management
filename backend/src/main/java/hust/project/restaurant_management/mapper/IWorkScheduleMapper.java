package hust.project.restaurant_management.mapper;

import hust.project.restaurant_management.entity.WorkScheduleEntity;
import hust.project.restaurant_management.entity.dto.request.CreateWorkScheduleRequest;
import hust.project.restaurant_management.model.WorkScheduleModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IWorkScheduleMapper {
    WorkScheduleModel toModelFromEntity(WorkScheduleEntity entity);

    WorkScheduleEntity toEntityFromModel(WorkScheduleModel model);

    WorkScheduleEntity toEntityFromRequest(CreateWorkScheduleRequest request);

    List<WorkScheduleEntity> toEntitiesFromModels(List<WorkScheduleModel> models);
}
