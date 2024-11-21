package hust.project.restaurant_management.usecase;

import hust.project.restaurant_management.constants.ImageTypeEnum;
import hust.project.restaurant_management.constants.ProductStatusEnum;
import hust.project.restaurant_management.constants.ProductTypeEnum;
import hust.project.restaurant_management.entity.ImageEntity;
import hust.project.restaurant_management.entity.ProductEntity;
import hust.project.restaurant_management.entity.StockEntity;
import hust.project.restaurant_management.entity.dto.request.CreateProductRequest;
import hust.project.restaurant_management.mapper.IProductMapper;
import hust.project.restaurant_management.port.IImagePort;
import hust.project.restaurant_management.port.IProductPort;
import hust.project.restaurant_management.port.IStockPort;
import hust.project.restaurant_management.utils.GenerateCodeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateProductUseCase {
    private final IProductPort productPort;
    private final IImagePort imagePort;
    private final IProductMapper productMapper;
    private final IStockPort stockPort;

    @Transactional
    public ProductEntity createProduct(CreateProductRequest request) {
        ProductEntity product = productMapper.toEntityFromRequest(request);
        product.setStatus(ProductStatusEnum.ACTIVE.name());
        product.setProductType(ProductTypeEnum.valueOf(request.getProductType()).name());

        Long maxId = productPort.getMaxId();
        product.setCode(GenerateCodeUtils.generateCode("SP", maxId + 1));

        var savedProduct = productPort.save(product);
        final Long productId = savedProduct.getId();

        List<ImageEntity> images = request.getImages().stream()
                .map(image -> ImageEntity.builder()
                        .url(image)
                        .entityId(productId)
                        .entityType(ImageTypeEnum.PRODUCT.name())
                        .build())
                .toList();

        savedProduct.setImages(imagePort.saveAll(images));

        StockEntity stock = StockEntity.builder()
                .productId(productId)
                .availableQuantity(0L)
                .soldQuantity(0L)
                .build();
        savedProduct.setStock(stockPort.save(stock));

        return savedProduct;
    }
}
