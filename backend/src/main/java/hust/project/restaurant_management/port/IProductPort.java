package hust.project.restaurant_management.port;

import hust.project.restaurant_management.entity.ProductEntity;
import hust.project.restaurant_management.entity.dto.request.GetProductRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IProductPort {
    ProductEntity save(ProductEntity productEntity);

    Pair<PageInfo, List<ProductEntity>> getAllProducts(GetProductRequest filter);

    ProductEntity getProductById(Long id);

    List<ProductEntity> getProductsByIds(List<Long> ids);

    void deleteProductById(Long id);

    Long getMaxId();
}
