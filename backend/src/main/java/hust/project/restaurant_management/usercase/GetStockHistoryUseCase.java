package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.entity.StockHistoryEntity;
import hust.project.restaurant_management.entity.StockHistoryItemEntity;
import hust.project.restaurant_management.entity.SupplierEntity;
import hust.project.restaurant_management.entity.UserEntity;
import hust.project.restaurant_management.entity.dto.request.GetStockHistoryRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.port.IStockHistoryItemPort;
import hust.project.restaurant_management.port.IStockHistoryPort;
import hust.project.restaurant_management.port.ISupplierPort;
import hust.project.restaurant_management.port.IUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetStockHistoryUseCase {
    private final IStockHistoryPort stockHistoryPort;
    private final ISupplierPort supplierPort;
    private final IUserPort userPort;
    private final IStockHistoryItemPort stockHistoryItemPort;

    public Pair<PageInfo, List<StockHistoryEntity>> getAllStockHistories(GetStockHistoryRequest filter) {
        var result = stockHistoryPort.getAllStockHistories(filter);
        var stockHistories = result.getSecond();

        List<SupplierEntity> suppliers = supplierPort.getSuppliersByIds(
                stockHistories.stream().map(StockHistoryEntity::getSupplierId).toList()
        );
        var mapIdToSupplier = suppliers.stream().collect(
                Collectors.toMap(SupplierEntity::getId, Function.identity())
        );

        List<UserEntity> users = userPort.getUsersByIds(
                stockHistories.stream().map(StockHistoryEntity::getUserId).toList()
        );
        var mapIdToUser = users.stream().collect(
                Collectors.toMap(UserEntity::getId, Function.identity())
        );

        List<StockHistoryItemEntity> stockHistoryItems = stockHistoryItemPort.getStockHistoryItemsByStockHistoryIds(
                stockHistories.stream().map(StockHistoryEntity::getId).toList()
        );


        stockHistories.forEach(stockHistory -> {
            stockHistory.setUser(mapIdToUser.getOrDefault(stockHistory.getUserId(), null));
            stockHistory.setSupplier(mapIdToSupplier.getOrDefault(stockHistory.getSupplierId(), null));

            stockHistory.setStockHistoryItems(stockHistoryItems.stream()
                    .filter(stockHistoryItem -> stockHistoryItem.getStockHistoryId().equals(stockHistory.getId()))
                    .toList());
        });

        return Pair.of(result.getFirst(), stockHistories);
    }

    public StockHistoryEntity getDetailStockHistory(Long id) {
        StockHistoryEntity stockHistory = stockHistoryPort.getStockHistoryById(id);

        SupplierEntity supplier = supplierPort.getSupplierById(stockHistory.getSupplierId());
        UserEntity user = userPort.getUserById(stockHistory.getUserId());

        stockHistory.setSupplier(supplier);
        stockHistory.setUser(user);

        List<StockHistoryItemEntity> stockHistoryItems = stockHistoryItemPort.getStockHistoryItemsByStockHistoryId(id);
        stockHistory.setStockHistoryItems(stockHistoryItems);

        return stockHistory;
    }
}
