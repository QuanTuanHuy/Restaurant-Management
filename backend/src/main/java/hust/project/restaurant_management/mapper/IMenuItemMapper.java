package hust.project.restaurant_management.mapper;

import hust.project.restaurant_management.entity.MenuItemEntity;
import hust.project.restaurant_management.entity.dto.request.CreateMenuItemRequest;
import hust.project.restaurant_management.model.MenuItemModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMenuItemMapper {
    MenuItemEntity toEntityFromModel(MenuItemModel model);

    MenuItemModel toModelFromEntity(MenuItemEntity entity);

    MenuItemEntity toEntityFromRequest(CreateMenuItemRequest request);

    List<MenuItemEntity> toEntitiesFromModels(List<MenuItemModel> models);
}
