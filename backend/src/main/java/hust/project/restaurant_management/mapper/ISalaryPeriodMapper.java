package hust.project.restaurant_management.mapper;

import hust.project.restaurant_management.entity.SalaryPeriodEntity;
import hust.project.restaurant_management.entity.dto.request.CreateSalaryPeriodRequest;
import hust.project.restaurant_management.model.SalaryPeriodModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ISalaryPeriodMapper {
    SalaryPeriodEntity toEntityFromModel(SalaryPeriodModel model);

    SalaryPeriodModel toModelFromEntity(SalaryPeriodEntity entity);

    SalaryPeriodEntity toEntityFromRequest(CreateSalaryPeriodRequest request);

    List<SalaryPeriodEntity> toEntitiesFromModels(List<SalaryPeriodModel> models);
}
