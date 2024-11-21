package hust.project.restaurant_management.usecase;

import hust.project.restaurant_management.constants.ImageTypeEnum;
import hust.project.restaurant_management.constants.ProductStatusEnum;
import hust.project.restaurant_management.entity.ProductEntity;
import hust.project.restaurant_management.entity.StockEntity;
import hust.project.restaurant_management.port.IImagePort;
import hust.project.restaurant_management.port.IProductPort;
import hust.project.restaurant_management.port.IStockPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteProductUseCase {
    private final IProductPort productPort;
    private final IStockPort stockPort;
    private final IImagePort imagePort;

    @Transactional
    public void deleteProduct(Long id) {
        ProductEntity product = productPort.getProductById(id);

        StockEntity stock = stockPort.getStockByProductId(id);
        if (stock.getAvailableQuantity() == 0 && stock.getSoldQuantity() == 0) {
            productPort.deleteProductById(id);
            stockPort.deleteStockById(stock.getId());
            imagePort.deleteImagesByEntityIdAndEntityType(id, ImageTypeEnum.PRODUCT.name());
            return;
        }

        product.setStatus(ProductStatusEnum.DELETED.name());

        productPort.save(product);
    }
}
