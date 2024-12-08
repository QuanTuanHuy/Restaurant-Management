package hust.project.restaurant_management.service.impl;

import hust.project.restaurant_management.entity.TableEntity;
import hust.project.restaurant_management.entity.dto.request.CreateTableRequest;
import hust.project.restaurant_management.entity.dto.request.GetTableAvailableRequest;
import hust.project.restaurant_management.entity.dto.request.GetTableRequest;
import hust.project.restaurant_management.entity.dto.request.UpdateTableRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.service.ITableService;
import hust.project.restaurant_management.usecase.CreateTableUseCase;
import hust.project.restaurant_management.usecase.DeleteTableUseCase;
import hust.project.restaurant_management.usecase.GetTableUseCase;
import hust.project.restaurant_management.usecase.UpdateTableUseCase;
import lombok.RequiredArgsConstructor;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.CachePut;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.cache.annotation.Caching;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TableService implements ITableService {
    private final CreateTableUseCase createTableUseCase;
    private final GetTableUseCase getTableUseCase;
    private final UpdateTableUseCase updateTableUseCase;
    private final DeleteTableUseCase deleteTableUseCase;

    @Override
//    @Caching(
//            evict = {
//                    @CacheEvict(value = "table", allEntries = true)
//            },
//            put = {
//                    @CachePut(value = "table", key = "#result.id")
//            }
//    )
    public TableEntity createTable(CreateTableRequest request) {
        return createTableUseCase.createTable(request);
    }

    @Override
    public Pair<PageInfo, List<TableEntity>> getAllTables(GetTableRequest filter) {
        return getTableUseCase.getAllTables(filter);
    }

    @Override
    public List<TableEntity> getAllTablesAvailable(GetTableAvailableRequest filter) {
        return getTableUseCase.getAllTablesAvailable(filter);
    }

    @Override
//    @Cacheable(value = "table")
    public List<TableEntity> getAllTables() {
        return getTableUseCase.getAllTables();
    }

    @Override
//    @Cacheable(value = "table", key = "#id")
    public TableEntity getDetailTable(Long id) {
        return getTableUseCase.getDetailTable(id);
    }

    @Override
//    @CachePut(value = "table", key = "#id")
    public TableEntity updateTable(Long id, UpdateTableRequest request) {
        return updateTableUseCase.updateTable(id, request);
    }

    @Override
//    @Caching(evict = {
//            @CacheEvict(value = "table", allEntries = true),
//            @CacheEvict(value = "table", key = "#id")
//    })
    public void deleteTable(Long id) {
        deleteTableUseCase.deleteTable(id);
    }
}
