package hust.project.restaurant_management.mapper;

import hust.project.restaurant_management.entity.ImageEntity;
import hust.project.restaurant_management.model.ImageModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IImageMapper {
    ImageEntity toEntityFromModel(ImageModel model);

    ImageModel toModelFromEntity(ImageEntity entity);

    List<ImageEntity> toEntitiesFromModels(List<ImageModel> models);

    List<ImageModel> toModelsFromEntities(List<ImageEntity> entities);
}
