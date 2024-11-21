package hust.project.restaurant_management.usecase;

import hust.project.restaurant_management.constants.ImageTypeEnum;
import hust.project.restaurant_management.constants.ProductStatusEnum;
import hust.project.restaurant_management.constants.ProductTypeEnum;
import hust.project.restaurant_management.entity.ImageEntity;
import hust.project.restaurant_management.entity.ProductEntity;
import hust.project.restaurant_management.entity.dto.request.UpdateProductRequest;
import hust.project.restaurant_management.port.IImagePort;
import hust.project.restaurant_management.port.IProductPort;
import hust.project.restaurant_management.port.IStockPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UpdateProductUseCase {
    private final IProductPort productPort;
    private final IImagePort imagePort;
    private final IStockPort stockPort;

    @Transactional
    public ProductEntity updateProduct(Long id, UpdateProductRequest request) {
        ProductEntity product = productPort.getProductById(id);

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setSellingPrice(request.getSellingPrice());
        product.setProductType(ProductTypeEnum.valueOf(request.getProductType()).name());
        product.setStatus(ProductStatusEnum.valueOf(request.getStatus()).name());
        product.setThumbnailImg(request.getThumbnailImg());


        List<ImageEntity> currentImages = imagePort.getImagesByEntityIdAndEntityType(id, ImageTypeEnum.PRODUCT.name());

        Set<String> currentImageUrls = currentImages.stream()
                .map(ImageEntity::getUrl)
                .collect(Collectors.toSet());


        Set<String> requestImageUrls = request.getImages();

        List<Long> deleteImageIds = currentImages.stream()
                .filter(image -> !requestImageUrls.contains(image.getUrl()))
                .map(ImageEntity::getId)
                .toList();

        if (!CollectionUtils.isEmpty(deleteImageIds)) {
            imagePort.deleteImagesByIds(deleteImageIds);
        }

        List<ImageEntity> newImages = requestImageUrls.stream()
                .filter(requestImageUrl -> !currentImageUrls.contains(requestImageUrl))
                .map(requestImageUrl -> ImageEntity.builder()
                        .entityId(id)
                        .url(requestImageUrl)
                        .entityType(ImageTypeEnum.PRODUCT.name())
                        .build())
                .toList();

        if (!CollectionUtils.isEmpty(newImages)) {
            imagePort.saveAll(newImages);
        }

        ProductEntity savedProduct = productPort.save(product);

        savedProduct.setImages(imagePort.getImagesByEntityIdAndEntityType(id, ImageTypeEnum.PRODUCT.name()));
        savedProduct.setStock(stockPort.getStockByProductId(id));

        return savedProduct;
    }
}
