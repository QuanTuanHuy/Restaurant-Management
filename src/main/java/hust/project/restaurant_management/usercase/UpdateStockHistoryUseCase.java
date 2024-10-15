package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.constants.StockHistoryStatusEnum;
import hust.project.restaurant_management.entity.StockHistoryEntity;
import hust.project.restaurant_management.entity.StockHistoryItemEntity;
import hust.project.restaurant_management.entity.SupplierEntity;
import hust.project.restaurant_management.entity.UserEntity;
import hust.project.restaurant_management.entity.dto.request.UpdateStockHistoryItemRequest;
import hust.project.restaurant_management.entity.dto.request.UpdateStockHistoryRequest;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.port.IStockHistoryItemPort;
import hust.project.restaurant_management.port.IStockHistoryPort;
import hust.project.restaurant_management.port.ISupplierPort;
import hust.project.restaurant_management.port.IUserPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateStockHistoryUseCase {
    private final IStockHistoryPort stockHistoryPort;
    private final IStockHistoryItemPort stockHistoryItemPort;
    private final ISupplierPort supplierPort;
    private final IUserPort userPort;
    private final UpdateStockUseCase updateStockUseCase;

    @Transactional
    public StockHistoryEntity updateStockHistory(Long id, UpdateStockHistoryRequest request) {
        StockHistoryEntity stockHistory = stockHistoryPort.getStockHistoryById(id);

        validateStockHistoryStatus(stockHistory.getStatus(), request.getStatus());

        SupplierEntity supplier = supplierPort.getSupplierById(request.getSupplierId());
        UserEntity user = userPort.getUserById(request.getUserId());

        if (StringUtils.hasText(request.getCode())){
            stockHistory.setCode(request.getCode());
        }
        stockHistory.setStatus(StockHistoryStatusEnum.valueOf(request.getStatus()).name());
        stockHistory.setNote(request.getNote());
        stockHistory.setSupplierId(request.getSupplierId());
        stockHistory.setUserId(request.getUserId());

        if (!request.getStockHistoryItems().isEmpty()) {
            Double totalPrice = request.getStockHistoryItems().stream()
                    .mapToDouble(item -> item.getPricePerUnit() * item.getQuantity())
                    .sum();
            stockHistory.setTotalPrice(totalPrice);
        }

        StockHistoryEntity savedStockHistory = stockHistoryPort.save(stockHistory);

        HashMap<Long, UpdateStockHistoryItemRequest> mapProductIdToUpdateItem = (HashMap<Long, UpdateStockHistoryItemRequest>)
                request.getStockHistoryItems()
                        .stream()
                        .collect(Collectors.toMap(UpdateStockHistoryItemRequest::getProductId, Function.identity()));

        List<StockHistoryItemEntity> currentStockHistoryItems = stockHistoryItemPort.getStockHistoryItemsByStockHistoryId(id);

        List<Long> deleteStockHistoryItemIds = currentStockHistoryItems.stream()
                .filter(item -> !mapProductIdToUpdateItem.containsKey(item.getProductId()))
                .map(StockHistoryItemEntity::getId)
                .toList();

        if (!CollectionUtils.isEmpty(deleteStockHistoryItemIds)) {
            stockHistoryItemPort.deleteStockHistoryItemsByIds(deleteStockHistoryItemIds);
        }

        List<StockHistoryItemEntity> modifiedStockHistoryItems = new ArrayList<>();
        HashSet<Long> existedProductIds = new HashSet<>();

        currentStockHistoryItems.stream()
                .filter(item -> mapProductIdToUpdateItem.containsKey(item.getProductId()))
                .forEach(item -> {
                    var requestItem = mapProductIdToUpdateItem.get(item.getProductId());

                    if (requestItem.getQuantity().equals(item.getQuantity()) && requestItem.getPricePerUnit().equals(item.getPricePerUnit())) {
                        return;
                    }

                    item.setQuantity(requestItem.getQuantity());
                    item.setPricePerUnit(requestItem.getPricePerUnit());
                    modifiedStockHistoryItems.add(item);
                    existedProductIds.add(item.getProductId());
                });

        request.getStockHistoryItems().forEach(item -> {
            if (!existedProductIds.contains(item.getProductId())) {
                StockHistoryItemEntity newItem = StockHistoryItemEntity.builder()
                        .productId(item.getProductId())
                        .quantity(item.getQuantity())
                        .stockHistoryId(id)
                        .build();
                modifiedStockHistoryItems.add(newItem);
            }
        });

        if (!CollectionUtils.isEmpty(modifiedStockHistoryItems)) {
            stockHistoryItemPort.saveAll(modifiedStockHistoryItems);
        }
        savedStockHistory.setSupplier(supplier);
        savedStockHistory.setUser(user);
        savedStockHistory.setStockHistoryItems(stockHistoryItemPort.getStockHistoryItemsByStockHistoryId(id));


        if(savedStockHistory.getStatus().equals(StockHistoryStatusEnum.DONE.name())) {
            updateStockUseCase.syncStock(id);
        }

        return savedStockHistory;
    }

    private void validateStockHistoryStatus(String oldStatus, String newStatus) {
        if (StockHistoryStatusEnum.PENDING.name().equals(oldStatus) && StockHistoryStatusEnum.DONE.name().equals(newStatus))
            return;
        log.error("[UpdateStockHistoryUseCase] wrong status transition: {} -> {}", oldStatus, newStatus);
        throw new AppException(ErrorCode.UPDATE_STOCK_HISTORY_FAILED);
    }
}
