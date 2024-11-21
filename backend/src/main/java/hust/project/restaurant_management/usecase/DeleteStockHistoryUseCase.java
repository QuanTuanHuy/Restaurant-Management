package hust.project.restaurant_management.usecase;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.constants.StockHistoryStatusEnum;
import hust.project.restaurant_management.entity.StockHistoryEntity;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.port.IStockHistoryItemPort;
import hust.project.restaurant_management.port.IStockHistoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteStockHistoryUseCase {
    private final IStockHistoryPort stockHistoryPort;
    private final IStockHistoryItemPort stockHistoryItemPort;

    @Transactional
    public void deleteStockHistory(Long id) {
        StockHistoryEntity stock = stockHistoryPort.getStockHistoryById(id);

        if (stock.getStatus().equals(StockHistoryStatusEnum.DONE.name())) {
            log.error("[DeleteStockHistoryUseCase] delete stock history failed: stock history is done");
            throw new AppException(ErrorCode.DELETE_STOCK_HISTORY_FAILED);
        }

        stockHistoryItemPort.deleteStockHistoryItemsByStockHistoryId(id);

        stockHistoryPort.deleteStockHistoryById(id);
    }
}
