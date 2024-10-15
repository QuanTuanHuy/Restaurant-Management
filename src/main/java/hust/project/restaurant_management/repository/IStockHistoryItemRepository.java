package hust.project.restaurant_management.repository;

import hust.project.restaurant_management.model.StockHistoryItemModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStockHistoryItemRepository extends IBaseRepository<StockHistoryItemModel> {
    List<StockHistoryItemModel> findByProductId(Long productId);

    List<StockHistoryItemModel> findByStockHistoryId(Long stockHistoryId);

    List<StockHistoryItemModel> findByStockHistoryIdIn(List<Long> stockHistoryIds);

    void deleteByIdIn(List<Long> ids);
}
