package hust.project.restaurant_management.repository;

import hust.project.restaurant_management.entity.dto.request.GetStockHistoryRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.model.StockHistoryModel;
import org.springframework.data.util.Pair;

import java.util.List;

public interface CustomStockHistoryRepository {
    Pair<PageInfo, List<StockHistoryModel>> getAllStockHistories(GetStockHistoryRequest filter);
}
