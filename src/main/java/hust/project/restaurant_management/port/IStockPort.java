package hust.project.restaurant_management.port;

import hust.project.restaurant_management.entity.StockEntity;

import java.util.List;

public interface IStockPort {
    StockEntity save(StockEntity stockEntity);

    StockEntity getStockByProductId(Long productId);

    List<StockEntity> saveAll(List<StockEntity> stockEntities);

    List<StockEntity> getStocksByProductIds(List<Long> productIds);

    List<StockEntity> getStocksByIds(List<Long> stockIds);

    void deleteStockById(Long id);
}
