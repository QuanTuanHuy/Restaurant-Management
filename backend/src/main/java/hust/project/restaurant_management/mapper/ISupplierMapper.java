package hust.project.restaurant_management.mapper;

import hust.project.restaurant_management.entity.SupplierEntity;
import hust.project.restaurant_management.entity.dto.request.CreateSupplierRequest;
import hust.project.restaurant_management.model.SupplierModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ISupplierMapper {
    SupplierModel toModelFromEntity(SupplierEntity entity);

    SupplierEntity toEntityFromModel(SupplierModel model);

    @Mapping(target = "status", ignore = true)
    SupplierEntity toEntityFromRequest(CreateSupplierRequest request);

    List<SupplierEntity> toEntitiesFromModels(List<SupplierModel> models);
}
