package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.constants.StockHistoryStatusEnum;
import hust.project.restaurant_management.entity.StockEntity;
import hust.project.restaurant_management.entity.StockHistoryEntity;
import hust.project.restaurant_management.entity.StockHistoryItemEntity;
import hust.project.restaurant_management.entity.dto.request.QuantityStockItemRequest;
import hust.project.restaurant_management.entity.dto.request.QuantityStockRequest;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.port.IStockHistoryItemPort;
import hust.project.restaurant_management.port.IStockHistoryPort;
import hust.project.restaurant_management.port.IStockPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateStockUseCase {
    private final IStockHistoryPort stockHistoryPort;
    private final IStockHistoryItemPort stockHistoryItemPort;
    private final IStockPort stockPort;

    @Transactional
    public void syncStock(Long stockHistoryId) {
        StockHistoryEntity stockHistory = stockHistoryPort.getStockHistoryById(stockHistoryId);

        if (!stockHistory.getStatus().equals(StockHistoryStatusEnum.DONE.name())) {
            log.error("[UpdateStockUseCase] cannot sync stock with status: {}", stockHistory.getStatus());
            throw new AppException(ErrorCode.SYNC_STOCK_FAILED);
        }

        List<StockHistoryItemEntity> stockHistoryItems = stockHistoryItemPort.getStockHistoryItemsByStockHistoryId(stockHistoryId);
        List<Long> productIds = stockHistoryItems.stream()
                .map(StockHistoryItemEntity::getProductId)
                .toList();

        List<StockEntity> stocks = stockPort.getStocksByProductIds(productIds);

        if (stocks.size() != productIds.size()) {
            log.error("[UpdateStockUseCase] cannot sync stock, stock not found");
            throw new AppException(ErrorCode.STOCK_NOT_FOUND);
        }

        var mapProductIdToStockHistoryItem = stockHistoryItems.stream()
                .collect(Collectors.toMap(StockHistoryItemEntity::getProductId, Function.identity()));


        stocks.forEach(stock -> {
            var stockHistoryItem = mapProductIdToStockHistoryItem.get(stock.getProductId());
            stock.setAvailableQuantity(stock.getAvailableQuantity() + stockHistoryItem.getQuantity());
        });

        stockPort.saveAll(stocks);
    }

    @Transactional
    public void subtractQuantityStock(QuantityStockRequest request) {
        List<Long> stockIds = request.getItems().stream()
                .map(QuantityStockItemRequest::getStockId)
                .toList();

        List<StockEntity> stocks = stockPort.getStocksByIds(stockIds);
        var mapIdToStock = stocks.stream()
                .collect(Collectors.toMap(StockEntity::getId, Function.identity()));

        if (stocks.size() != stockIds.size()) {
            log.error("[UpdateStockUseCase] cannot subtract quantity stock, stock not found");
            throw new AppException(ErrorCode.STOCK_NOT_FOUND);
        }

        request.getItems().forEach(item -> {
            var stock = mapIdToStock.get(item.getStockId());

            if (stock.getAvailableQuantity() < item.getQuantity()) {
                throw new AppException(ErrorCode.STOCK_NOT_ENOUGH);
            }

            stock.setAvailableQuantity(stock.getAvailableQuantity() - item.getQuantity());
            stock.setSoldQuantity(stock.getSoldQuantity() + item.getQuantity());
        });

        stockPort.saveAll(stocks);
    }
}
