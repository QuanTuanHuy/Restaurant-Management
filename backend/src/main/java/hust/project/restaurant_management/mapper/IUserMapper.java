package hust.project.restaurant_management.mapper;

import hust.project.restaurant_management.entity.UserEntity;
import hust.project.restaurant_management.entity.dto.request.CreateUserRequest;
import hust.project.restaurant_management.entity.dto.request.UpdateUserRequest;
import hust.project.restaurant_management.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserMapper {
    UserEntity toEntityFromModel(UserModel userModel);

    UserModel toModelFromEntity(UserEntity userEntity);

    List<UserEntity> toEntitiesFromModels(List<UserModel> userModels);

    UserEntity toEntityFromRequest(UpdateUserRequest request);

    @Mapping(target = "password", ignore = true)
    UserEntity toEntityFromRequest(CreateUserRequest request);
}
