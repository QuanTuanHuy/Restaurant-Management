package hust.project.restaurant_management.repository.adapter;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.entity.StockEntity;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.mapper.IStockMapper;
import hust.project.restaurant_management.model.StockModel;
import hust.project.restaurant_management.port.IStockPort;
import hust.project.restaurant_management.repository.IStockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockAdapter implements IStockPort {
    private final IStockRepository stockRepository;
    private final IStockMapper stockMapper;

    @Override
    public StockEntity save(StockEntity stockEntity) {
        try {
            StockModel stockModel = stockMapper.toModelFromEntity(stockEntity);
            return stockMapper.toEntityFromModel(stockRepository.save(stockModel));
        } catch (Exception e) {
            log.error("[StockAdapter] save stock failed, error:, {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_STOCK_FAILED);
        }
    }

    @Override
    public StockEntity getStockByProductId(Long productId) {
        return stockMapper.toEntityFromModel(stockRepository.findByProductId(productId).orElseThrow(
                () -> new AppException(ErrorCode.STOCK_NOT_FOUND)
        ));
    }

    @Override
    public List<StockEntity> saveAll(List<StockEntity> stockEntities) {
        try {
            List<StockModel> stockModels = stockMapper.toModelsFromEntities(stockEntities);
            return stockMapper.toEntitiesFromModels(stockRepository.saveAll(stockModels));
        } catch (Exception e) {
            log.error("[StockAdapter] save all stocks failed, error: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_STOCK_FAILED);
        }
    }

    @Override
    public List<StockEntity> getStocksByProductIds(List<Long> productIds) {
        return stockMapper.toEntitiesFromModels(stockRepository.findByProductIdIn(productIds));
    }

    @Override
    public List<StockEntity> getStocksByIds(List<Long> stockIds) {
        return stockMapper.toEntitiesFromModels(stockRepository.findByIdIn(stockIds));
    }

    @Override
    public void deleteStockById(Long id) {
        try {
            stockRepository.deleteById(id);
        } catch (Exception e) {
            log.error("[StockAdapter] delete stock failed, error: {}", e.getMessage());
            throw new AppException(ErrorCode.DELETE_STOCK_FAILED);
        }
    }
}
