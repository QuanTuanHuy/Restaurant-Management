package hust.project.restaurant_management.service;

import hust.project.restaurant_management.entity.TableEntity;
import hust.project.restaurant_management.entity.dto.request.CreateTableRequest;
import hust.project.restaurant_management.entity.dto.request.GetTableRequest;
import hust.project.restaurant_management.entity.dto.request.UpdateTableRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface ITableService {
    TableEntity createTable(CreateTableRequest request);

    Pair<PageInfo, List<TableEntity>> getAllTables(GetTableRequest filter);

    TableEntity getDetailTable(Long id);

    TableEntity updateTable(Long id, UpdateTableRequest request);

    void deleteTable(Long id);
}
