package hust.project.restaurant_management.usecase;

import hust.project.restaurant_management.constants.StockHistoryStatusEnum;
import hust.project.restaurant_management.entity.*;
import hust.project.restaurant_management.entity.dto.request.GetStockHistoryRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.port.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetStockHistoryUseCase {
    private final IStockHistoryPort stockHistoryPort;
    private final ISupplierPort supplierPort;
    private final IUserPort userPort;
    private final IProductPort productPort;
    private final IStockHistoryItemPort stockHistoryItemPort;

    public Pair<PageInfo, List<StockHistoryEntity>> getAllStockHistories(GetStockHistoryRequest filter) {
        if (CollectionUtils.isEmpty(filter.getStatuses())) {
            filter.setStatuses(List.of(StockHistoryStatusEnum.PROCESSING.name(),
                    StockHistoryStatusEnum.DONE.name(),
                    StockHistoryStatusEnum.PENDING.name()));
        }

        var result = stockHistoryPort.getAllStockHistories(filter);
        var stockHistories = result.getSecond();

        if (stockHistories.isEmpty()) {
            return result;
        }

        List<SupplierEntity> suppliers = supplierPort.getSuppliersByIds(
                stockHistories.stream().map(StockHistoryEntity::getSupplierId).distinct().toList()
        );
        var mapIdToSupplier = suppliers.stream().collect(
                Collectors.toMap(SupplierEntity::getId, Function.identity())
        );

        List<UserEntity> users = userPort.getUsersByIds(
                stockHistories.stream().map(StockHistoryEntity::getUserId).distinct().toList()
        );
        var mapIdToUser = users.stream().collect(
                Collectors.toMap(UserEntity::getId, Function.identity())
        );

        List<StockHistoryItemEntity> stockHistoryItems = stockHistoryItemPort.getStockHistoryItemsByStockHistoryIds(
                stockHistories.stream().map(StockHistoryEntity::getId).toList()
        );

        setProductToStockHistoryItems(stockHistoryItems);


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

        setProductToStockHistoryItems(stockHistoryItems);

        stockHistory.setStockHistoryItems(stockHistoryItems);

        return stockHistory;
    }

    private void setProductToStockHistoryItems(List<StockHistoryItemEntity> stockHistoryItems) {
        List<Long> productIds = stockHistoryItems.stream().map(StockHistoryItemEntity::getProductId).distinct().toList();
        List<ProductEntity> products = productPort.getProductsByIds(productIds);
        var mapIdToProduct = products.stream().collect(Collectors.toMap(ProductEntity::getId, Function.identity()));

        stockHistoryItems.forEach(si ->
                si.setProduct(mapIdToProduct.getOrDefault(si.getProductId(), null)));
    }
}
