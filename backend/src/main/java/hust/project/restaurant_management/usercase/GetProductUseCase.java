package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.constants.ImageTypeEnum;
import hust.project.restaurant_management.entity.ImageEntity;
import hust.project.restaurant_management.entity.ProductEntity;
import hust.project.restaurant_management.entity.StockEntity;
import hust.project.restaurant_management.entity.dto.request.GetProductRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.port.IImagePort;
import hust.project.restaurant_management.port.IProductPort;
import hust.project.restaurant_management.port.IStockPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetProductUseCase {
    private final IProductPort productPort;
    private final IImagePort imagePort;
    private final IStockPort stockPort;

    public Pair<PageInfo, List<ProductEntity>> getAllProducts(GetProductRequest filter) {
        var result = productPort.getAllProducts(filter);
        var products = result.getSecond();

        if (products.isEmpty()) {
            return result;
        }

        List<Long> productIds = products.stream().map(ProductEntity::getId).toList();

        List<ImageEntity> images = imagePort.getImagesByEntityIdsAndEntityType(
            productIds,
            ImageTypeEnum.PRODUCT.name()
        );

        products.forEach(product ->
            product.setImages(images.stream().filter(image -> image.getEntityId().equals(product.getId())).toList())
        );


        List<StockEntity> stocks = stockPort.getStocksByProductIds(productIds);

        var mapProductIdToStock = stocks.stream()
                .collect(Collectors.toMap(StockEntity::getProductId, Function.identity()));

        products.forEach(product -> product.setStock(mapProductIdToStock.getOrDefault(product.getId(), null)));

        return Pair.of(result.getFirst(), products);
    }

    public ProductEntity getProductDetail(Long id) {
        ProductEntity product = productPort.getProductById(id);

        product.setImages(imagePort.getImagesByEntityIdAndEntityType(id, ImageTypeEnum.PRODUCT.name()));
        product.setStock(stockPort.getStockByProductId(id));

        return product;
    }
}
