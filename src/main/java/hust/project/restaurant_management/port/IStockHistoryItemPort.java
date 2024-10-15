package hust.project.restaurant_management.port;

import hust.project.restaurant_management.entity.StockHistoryItemEntity;

import java.util.List;

public interface IStockHistoryItemPort {
    StockHistoryItemEntity save(StockHistoryItemEntity stockHistoryItemEntity);

    List<StockHistoryItemEntity> saveAll(List<StockHistoryItemEntity> stockHistoryItemEntities);

    List<StockHistoryItemEntity> getStockHistoryItemsByStockHistoryId(Long stockHistoryId);

    List<StockHistoryItemEntity> getStockHistoryItemsByStockHistoryIds(List<Long> stockHistoryIds);

    List<StockHistoryItemEntity> getStockHistoryItemsByProductId(Long productId);

    void deleteStockHistoryItemsByIds(List<Long> ids);
}
