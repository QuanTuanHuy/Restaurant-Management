package hust.project.restaurant_management.port;

import hust.project.restaurant_management.entity.TableEntity;
import hust.project.restaurant_management.entity.dto.request.GetTableAvailableRequest;
import hust.project.restaurant_management.entity.dto.request.GetTableRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface ITablePort {
    TableEntity save(TableEntity tableEntity);

    Pair<PageInfo, List<TableEntity>> getAllTables(GetTableRequest filter);

    List<TableEntity> getAllTables();

    List<TableEntity> getTablesByIds(List<Long> ids);

    List<TableEntity> getAllTablesAvailable(GetTableAvailableRequest filter);

    TableEntity getTableById(Long id);

    void deleteTableById(Long id);
}
