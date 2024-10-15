package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.constants.StockHistoryStatusEnum;
import hust.project.restaurant_management.entity.StockHistoryEntity;
import hust.project.restaurant_management.entity.StockHistoryItemEntity;
import hust.project.restaurant_management.entity.SupplierEntity;
import hust.project.restaurant_management.entity.UserEntity;
import hust.project.restaurant_management.entity.dto.request.CreateStockHistoryRequest;
import hust.project.restaurant_management.mapper.IStockHistoryMapper;
import hust.project.restaurant_management.port.IStockHistoryItemPort;
import hust.project.restaurant_management.port.IStockHistoryPort;
import hust.project.restaurant_management.port.ISupplierPort;
import hust.project.restaurant_management.port.IUserPort;
import hust.project.restaurant_management.utils.GenerateCodeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateStockHistoryUseCase {
    private final IStockHistoryPort stockHistoryPort;
    private final IStockHistoryItemPort stockHistoryItemPort;
    private final IStockHistoryMapper stockHistoryMapper;
    private final ISupplierPort supplierPort;
    private final IUserPort userPort;
    private final UpdateStockUseCase updateStockUseCase;

    @Transactional
    public StockHistoryEntity createStockHistory(CreateStockHistoryRequest request) {
        SupplierEntity supplier = supplierPort.getSupplierById(request.getSupplierId());

        UserEntity user = userPort.getUserById(request.getUserId());

        StockHistoryEntity stockHistory = stockHistoryMapper.toEntityFromRequest(request);
        stockHistory.setStatus(StockHistoryStatusEnum.valueOf(request.getStatus()).name());

        Double totalPrice = request.getStockHistoryItems().stream()
                .mapToDouble(item -> item.getPricePerUnit() * item.getQuantity()).sum();
        stockHistory.setTotalPrice(totalPrice);

        if (!StringUtils.hasText(request.getCode())) {
            stockHistory.setCode(GenerateCodeUtils.generateCode("SH", stockHistoryPort.getMaxId()) + 1);
        }

        StockHistoryEntity saveStockHistory = stockHistoryPort.save(stockHistory);


        final Long stockHistoryId = saveStockHistory.getId();

        List<StockHistoryItemEntity> stockHistoryItems = request.getStockHistoryItems().stream()
                .map(item -> StockHistoryItemEntity.builder()
                        .stockHistoryId(stockHistoryId)
                        .productId(item.getProductId())
                        .quantity(item.getQuantity())
                        .pricePerUnit(item.getPricePerUnit())
                        .build())
                .toList();

        saveStockHistory.setStockHistoryItems(stockHistoryItemPort.saveAll(stockHistoryItems));
        saveStockHistory.setSupplier(supplier);
        saveStockHistory.setUser(user);


        if(stockHistory.getStatus().equals(StockHistoryStatusEnum.DONE.name())) {
            updateStockUseCase.syncStock(stockHistoryId);
        }

        return saveStockHistory;
    }

}
