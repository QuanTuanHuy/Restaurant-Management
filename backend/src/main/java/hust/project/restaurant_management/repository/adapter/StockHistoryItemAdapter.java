package hust.project.restaurant_management.repository.adapter;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.entity.StockHistoryItemEntity;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.mapper.IStockHistoryItemMapper;
import hust.project.restaurant_management.model.StockHistoryItemModel;
import hust.project.restaurant_management.port.IStockHistoryItemPort;
import hust.project.restaurant_management.repository.IStockHistoryItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockHistoryItemAdapter implements IStockHistoryItemPort {
    private final IStockHistoryItemRepository stockHistoryItemRepository;
    private final IStockHistoryItemMapper stockHistoryItemMapper;

    @Override
    public StockHistoryItemEntity save(StockHistoryItemEntity stockHistoryItemEntity) {
        try {
            StockHistoryItemModel stockHistoryItemModel = stockHistoryItemMapper.toModelFromEntity(stockHistoryItemEntity);
            return stockHistoryItemMapper.toEntityFromModel(stockHistoryItemRepository.save(stockHistoryItemModel));
        } catch (Exception e) {
            log.error("[StockHistoryItemAdapter] save stock history item failed: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_STOCK_HISTORY_ITEM_FAILED);
        }
    }

    @Override
    public List<StockHistoryItemEntity> saveAll(List<StockHistoryItemEntity> stockHistoryItemEntities) {
        try {
            List<StockHistoryItemModel> stockHistoryItemModels = stockHistoryItemMapper.toModelsFromEntities(stockHistoryItemEntities);
            return stockHistoryItemMapper.toEntitiesFromModels(stockHistoryItemRepository.saveAll(stockHistoryItemModels));
        } catch (Exception e) {
            log.error("[StockHistoryItemAdapter] save all stock history items failed: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_STOCK_HISTORY_ITEM_FAILED);
        }
    }

    @Override
    public List<StockHistoryItemEntity> getStockHistoryItemsByStockHistoryId(Long stockHistoryId) {
        return stockHistoryItemMapper.toEntitiesFromModels(stockHistoryItemRepository.findByStockHistoryId(stockHistoryId));
    }

    @Override
    public List<StockHistoryItemEntity> getStockHistoryItemsByStockHistoryIds(List<Long> stockHistoryIds) {
        return stockHistoryItemMapper.toEntitiesFromModels(stockHistoryItemRepository.findByStockHistoryIdIn(stockHistoryIds));
    }

    @Override
    public List<StockHistoryItemEntity> getStockHistoryItemsByProductId(Long productId) {
        return stockHistoryItemMapper.toEntitiesFromModels(stockHistoryItemRepository.findByProductId(productId));
    }

    @Override
    public void deleteStockHistoryItemsByIds(List<Long> ids) {
        try {
            stockHistoryItemRepository.deleteByIdIn(ids);
        } catch (Exception e) {
            log.error("[StockHistoryItemAdapter] delete stock history item failed: {}", e.getMessage());
            throw new AppException(ErrorCode.DELETE_STOCK_HISTORY_ITEM_FAILED);
        }
    }

    @Override
    public void deleteStockHistoryItemsByStockHistoryId(Long stockHistoryId) {
        try {
            stockHistoryItemRepository.deleteByStockHistoryId(stockHistoryId);
        } catch (Exception e) {
            log.error("[StockHistoryItemAdapter] delete stock history item failed: {}", e.getMessage());
            throw new AppException(ErrorCode.DELETE_STOCK_HISTORY_ITEM_FAILED);
        }
    }
}
