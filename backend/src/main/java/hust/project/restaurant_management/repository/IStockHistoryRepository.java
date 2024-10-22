package hust.project.restaurant_management.repository;

import hust.project.restaurant_management.model.StockHistoryModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IStockHistoryRepository extends IBaseRepository<StockHistoryModel> {
    @Query("SELECT MAX(s.id) FROM StockHistoryModel s")
    Long getMaxId();
}
