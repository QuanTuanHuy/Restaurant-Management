package hust.project.restaurant_management.mapper;

import hust.project.restaurant_management.entity.MenuSectionEntity;
import hust.project.restaurant_management.entity.dto.request.CreateMenuSectionRequest;
import hust.project.restaurant_management.model.MenuSectionModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMenuSectionMapper {
    MenuSectionEntity toEntityFromModel(MenuSectionModel model);

    MenuSectionModel toModelFromEntity(MenuSectionEntity entity);

    MenuSectionEntity toEntityFromRequest(CreateMenuSectionRequest request);

    List<MenuSectionEntity> toEntitiesFromModels(List<MenuSectionModel> models);
}
