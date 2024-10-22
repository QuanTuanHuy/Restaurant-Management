package hust.project.restaurant_management.mapper;


import hust.project.restaurant_management.entity.RoleEntity;
import hust.project.restaurant_management.entity.dto.request.CreateRoleRequest;
import hust.project.restaurant_management.model.RoleModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IRoleMapper {
    RoleEntity toEntityFromModel(RoleModel roleModel);

    List<RoleEntity> toEntitiesFromModels(List<RoleModel> roleModels);

    RoleModel toModelFromEntity(RoleEntity roleEntity);

    RoleEntity toEntityFromRequest(CreateRoleRequest request);
}
