package hust.project.restaurant_management.repository;

import hust.project.restaurant_management.model.StockModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IStockRepository extends IBaseRepository<StockModel> {
    Optional<StockModel> findByProductId(Long productId);

    List<StockModel> findByProductIdIn(List<Long> productIds);

    List<StockModel> findByIdIn(List<Long> ids);
}
