package hust.project.restaurant_management.service.impl;

import hust.project.restaurant_management.entity.ProductEntity;
import hust.project.restaurant_management.entity.dto.request.CreateProductRequest;
import hust.project.restaurant_management.entity.dto.request.GetProductRequest;
import hust.project.restaurant_management.entity.dto.request.UpdateProductRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.service.IProductService;
import hust.project.restaurant_management.usecase.CreateProductUseCase;
import hust.project.restaurant_management.usecase.DeleteProductUseCase;
import hust.project.restaurant_management.usecase.GetProductUseCase;
import hust.project.restaurant_management.usecase.UpdateProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final CreateProductUseCase createProductUseCase;
    private final GetProductUseCase getProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;

    @Override
    @CachePut(value = "product", key = "#result.id")
    public ProductEntity createProduct(CreateProductRequest request) {
        return createProductUseCase.createProduct(request);
    }

    @Override
    public Pair<PageInfo, List<ProductEntity>> getAllProducts(GetProductRequest filter) {
        return getProductUseCase.getAllProducts(filter);
    }

    @Override
    @Cacheable(value = "product", key = "#id")
    public ProductEntity getProductDetail(Long id) {
        return getProductUseCase.getProductDetail(id);
    }

    @Override
    @CachePut(value = "product", key = "#id")
    public ProductEntity updateProduct(Long id, UpdateProductRequest request) {
        return updateProductUseCase.updateProduct(id, request);
    }

    @Override
    @CacheEvict(value = "product", key = "#id")
    public void deleteProduct(Long id) {
        deleteProductUseCase.deleteProduct(id);
    }
}
