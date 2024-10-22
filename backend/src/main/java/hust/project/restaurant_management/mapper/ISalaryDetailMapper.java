package hust.project.restaurant_management.mapper;

import hust.project.restaurant_management.entity.SalaryDetailEntity;
import hust.project.restaurant_management.model.SalaryDetailModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ISalaryDetailMapper {
    SalaryDetailEntity toEntityFromModel(SalaryDetailModel model);

    SalaryDetailModel toModelFromEntity(SalaryDetailEntity entity);

    List<SalaryDetailEntity> toEntitiesFromModels(List<SalaryDetailModel> models);

    List<SalaryDetailModel> toModelsFromEntities(List<SalaryDetailEntity> entities);
}
