package hust.project.restaurant_management.mapper;

import hust.project.restaurant_management.entity.ProductEntity;
import hust.project.restaurant_management.entity.dto.request.CreateProductRequest;
import hust.project.restaurant_management.model.ProductModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IProductMapper {
    ProductModel toModelFromEntity(ProductEntity entity);

    ProductEntity toEntityFromModel(ProductModel model);

    @Mapping(target = "images", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "productType", ignore = true)
    ProductEntity toEntityFromRequest(CreateProductRequest request);

    List<ProductEntity> toEntitiesFromModels(List<ProductModel> models);
}
