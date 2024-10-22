package hust.project.restaurant_management.service;

import hust.project.restaurant_management.entity.ProductEntity;
import hust.project.restaurant_management.entity.dto.request.CreateProductRequest;
import hust.project.restaurant_management.entity.dto.request.GetProductRequest;
import hust.project.restaurant_management.entity.dto.request.UpdateProductRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IProductService {
    ProductEntity createProduct(CreateProductRequest request);

    Pair<PageInfo, List<ProductEntity>> getAllProducts(GetProductRequest filter);

    ProductEntity getProductDetail(Long id);

    ProductEntity updateProduct(Long id, UpdateProductRequest request);

    void deleteProduct(Long id);
}
