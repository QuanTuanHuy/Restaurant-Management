package hust.project.restaurant_management.service.impl;

import hust.project.restaurant_management.entity.dto.request.QuantityStockRequest;
import hust.project.restaurant_management.service.IStockService;
import hust.project.restaurant_management.usercase.UpdateStockUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService implements IStockService {
    private final UpdateStockUseCase updateStockUseCase;

    @Override
    public void subtractQuantityStock(QuantityStockRequest request) {
        updateStockUseCase.subtractQuantityStock(request);
    }
}
