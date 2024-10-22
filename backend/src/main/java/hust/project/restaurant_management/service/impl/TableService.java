package hust.project.restaurant_management.service.impl;

import hust.project.restaurant_management.entity.TableEntity;
import hust.project.restaurant_management.entity.dto.request.CreateTableRequest;
import hust.project.restaurant_management.entity.dto.request.GetTableAvailableRequest;
import hust.project.restaurant_management.entity.dto.request.GetTableRequest;
import hust.project.restaurant_management.entity.dto.request.UpdateTableRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.service.ITableService;
import hust.project.restaurant_management.usercase.CreateTableUseCase;
import hust.project.restaurant_management.usercase.DeleteTableUseCase;
import hust.project.restaurant_management.usercase.GetTableUseCase;
import hust.project.restaurant_management.usercase.UpdateTableUseCase;
import lombok.RequiredArgsConstructor;
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
    public TableEntity getDetailTable(Long id) {
        return getTableUseCase.getDetailTable(id);
    }

    @Override
    public TableEntity updateTable(Long id, UpdateTableRequest request) {
        return updateTableUseCase.updateTable(id, request);
    }

    @Override
    public void deleteTable(Long id) {
        deleteTableUseCase.deleteTable(id);
    }
}
