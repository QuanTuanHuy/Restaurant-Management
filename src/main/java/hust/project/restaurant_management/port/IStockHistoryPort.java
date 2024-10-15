package hust.project.restaurant_management.port;

import hust.project.restaurant_management.entity.StockHistoryEntity;
import hust.project.restaurant_management.entity.dto.request.GetStockHistoryRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IStockHistoryPort {
    StockHistoryEntity save(StockHistoryEntity stockHistoryEntity);

    Pair<PageInfo, List<StockHistoryEntity>> getAllStockHistories(GetStockHistoryRequest filter);

    StockHistoryEntity getStockHistoryById(Long id);

    Long getMaxId();
}
