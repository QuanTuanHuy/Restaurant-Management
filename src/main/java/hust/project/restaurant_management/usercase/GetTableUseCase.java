package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.entity.TableEntity;
import hust.project.restaurant_management.entity.dto.request.GetTableRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.port.ITablePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetTableUseCase {
    private final ITablePort tablePort;

    public Pair<PageInfo, List<TableEntity>> getAllTables(GetTableRequest filter) {
        return tablePort.getAllTables(filter);
    }

    public TableEntity getDetailTable(Long id) {
        return tablePort.getTableById(id);
    }
}
