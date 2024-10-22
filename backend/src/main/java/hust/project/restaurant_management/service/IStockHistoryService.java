package hust.project.restaurant_management.service;

import hust.project.restaurant_management.entity.StockHistoryEntity;
import hust.project.restaurant_management.entity.dto.request.CreateStockHistoryRequest;
import hust.project.restaurant_management.entity.dto.request.GetStockHistoryRequest;
import hust.project.restaurant_management.entity.dto.request.UpdateStockHistoryRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IStockHistoryService {
    StockHistoryEntity createStockHistory(CreateStockHistoryRequest request);

    Pair<PageInfo, List<StockHistoryEntity>> getAllStockHistories(GetStockHistoryRequest filter);

    StockHistoryEntity getDetailStockHistory(Long id);

    StockHistoryEntity updateStockHistory(Long id, UpdateStockHistoryRequest request);

    void deleteStockHistory(Long id);
}
