package hust.project.restaurant_management.repository.adapter;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.entity.ProductEntity;
import hust.project.restaurant_management.entity.dto.request.GetProductRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.mapper.IProductMapper;
import hust.project.restaurant_management.model.ProductModel;
import hust.project.restaurant_management.port.IProductPort;
import hust.project.restaurant_management.repository.IProductRepository;
import hust.project.restaurant_management.repository.specification.ProductSpecification;
import hust.project.restaurant_management.utils.PageInfoUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductAdapter implements IProductPort {
    private final IProductRepository productRepository;
    private final IProductMapper productMapper;

    @Override
    public ProductEntity save(ProductEntity productEntity) {
        try {
            ProductModel productModel = productMapper.toModelFromEntity(productEntity);
            return productMapper.toEntityFromModel(productRepository.save(productModel));
        } catch (Exception e) {
            log.error("[ProductAdapter] save product failed: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_PRODUCT_FAILED);
        }
    }

    @Override
    public Pair<PageInfo, List<ProductEntity>> getAllProducts(GetProductRequest filter) {
        Sort sort;

        if (filter.getSortType().equals("asc")) {
            sort = Sort.by(filter.getSortBy()).ascending();
        } else {
            sort = Sort.by(filter.getSortBy()).descending();
        }

        Pageable pageable = PageRequest.of(Math.toIntExact(filter.getPage()), Math.toIntExact(filter.getPageSize()), sort);

        var result = productRepository.findAll(ProductSpecification.getAllProducts(filter), pageable);

        var pageInfo = PageInfoUtils.getPageInfo(result);

        return Pair.of(pageInfo, productMapper.toEntitiesFromModels(result.getContent()));
    }

    @Override
    public ProductEntity getProductById(Long id) {
        return productMapper.toEntityFromModel(productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND)));
    }

    @Override
    public List<ProductEntity> getProductsByIds(List<Long> ids) {
        return productMapper.toEntitiesFromModels(productRepository.findByIdIn(ids));
    }

    @Override
    public void deleteProductById(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            log.error("[ProductAdapter] delete product failed: {}", e.getMessage());
            throw new AppException(ErrorCode.DELETE_PRODUCT_FAILED);
        }
    }

    @Override
    public Long getMaxId() {
        var result = productRepository.getMaxId();
        return result == null ? 0L : result;
    }
}
