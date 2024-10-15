package hust.project.restaurant_management.repository.adapter;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.entity.StockHistoryEntity;
import hust.project.restaurant_management.entity.dto.request.GetStockHistoryRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.mapper.IStockHistoryMapper;
import hust.project.restaurant_management.model.StockHistoryModel;
import hust.project.restaurant_management.port.IStockHistoryPort;
import hust.project.restaurant_management.repository.IStockHistoryRepository;
import hust.project.restaurant_management.repository.specification.StockHistorySpecification;
import hust.project.restaurant_management.utils.PageInfoUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockHistoryAdapter implements IStockHistoryPort {
    private final IStockHistoryRepository stockHistoryRepository;
    private final IStockHistoryMapper stockHistoryMapper;

    @Override
    public StockHistoryEntity save(StockHistoryEntity stockHistoryEntity) {
        try {
            StockHistoryModel stockHistoryModel = stockHistoryMapper.toModelFromEntity(stockHistoryEntity);
            return stockHistoryMapper.toEntityFromModel(stockHistoryRepository.save(stockHistoryModel));
        } catch (Exception e) {
            log.error("[StockHistoryAdapter] save stock history failed: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_STOCK_HISTORY_FAILED);
        }
    }

    @Override
    public Pair<PageInfo, List<StockHistoryEntity>> getAllStockHistories(GetStockHistoryRequest filter) {
        Pageable pageable = PageRequest.of(Math.toIntExact(filter.getPage()), Math.toIntExact(filter.getPageSize()),
                Sort.by("dateTime").descending());

        // TODO: implement later

        var result = stockHistoryRepository.findAll(StockHistorySpecification.getAllStockHistories(filter), pageable);

        var pageInfo = PageInfoUtils.getPageInfo(result);

        return Pair.of(pageInfo, stockHistoryMapper.toEntitiesFromModels(result.getContent()));
    }

    @Override
    public StockHistoryEntity getStockHistoryById(Long id) {
        return stockHistoryMapper.toEntityFromModel(stockHistoryRepository.findById(id).orElseThrow(() -> {
            log.error("[StockHistoryAdapter] get stock history by id failed, {}", id);
            return new AppException(ErrorCode.STOCK_HISTORY_NOT_FOUND);
        }));
    }

    @Override
    public Long getMaxId() {
        var result = stockHistoryRepository.getMaxId();
        return result == null ? 0L : result;
    }

    @Override
    public void deleteStockHistoryById(Long id) {
        try {
            stockHistoryRepository.deleteById(id);
        } catch (Exception e) {
            log.error("[StockHistoryAdapter] delete stock history failed: {}", e.getMessage());
            throw new AppException(ErrorCode.DELETE_STOCK_HISTORY_FAILED);
        }
    }
}
