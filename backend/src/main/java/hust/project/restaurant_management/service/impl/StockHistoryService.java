package hust.project.restaurant_management.service.impl;

import hust.project.restaurant_management.entity.StockHistoryEntity;
import hust.project.restaurant_management.entity.dto.request.CreateStockHistoryRequest;
import hust.project.restaurant_management.entity.dto.request.GetStockHistoryRequest;
import hust.project.restaurant_management.entity.dto.request.UpdateStockHistoryRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.service.IStockHistoryService;
import hust.project.restaurant_management.usecase.CreateStockHistoryUseCase;
import hust.project.restaurant_management.usecase.DeleteStockHistoryUseCase;
import hust.project.restaurant_management.usecase.GetStockHistoryUseCase;
import hust.project.restaurant_management.usecase.UpdateStockHistoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockHistoryService implements IStockHistoryService {
    private final CreateStockHistoryUseCase createStockHistoryUseCase;
    private final GetStockHistoryUseCase getStockHistoryUseCase;
    private final UpdateStockHistoryUseCase updateStockHistoryUseCase;
    private final DeleteStockHistoryUseCase deleteStockHistoryUseCase;

    @Override
    public StockHistoryEntity createStockHistory(CreateStockHistoryRequest request) {
        return createStockHistoryUseCase.createStockHistory(request);
    }

    @Override
    public Pair<PageInfo, List<StockHistoryEntity>> getAllStockHistories(GetStockHistoryRequest filter) {
        return getStockHistoryUseCase.getAllStockHistories(filter);
    }

    @Override
    public StockHistoryEntity getDetailStockHistory(Long id) {
        return getStockHistoryUseCase.getDetailStockHistory(id);
    }

    @Override
    public StockHistoryEntity updateStockHistory(Long id, UpdateStockHistoryRequest request) {
        return updateStockHistoryUseCase.updateStockHistory(id, request);
    }

    @Override
    public void deleteStockHistory(Long id) {
        deleteStockHistoryUseCase.deleteStockHistory(id);
    }
}
