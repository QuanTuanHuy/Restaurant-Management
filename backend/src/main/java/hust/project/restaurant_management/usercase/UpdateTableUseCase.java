package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.constants.TableTypeEnum;
import hust.project.restaurant_management.entity.TableEntity;
import hust.project.restaurant_management.entity.dto.request.UpdateTableRequest;
import hust.project.restaurant_management.port.ITablePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateTableUseCase {
    private final ITablePort tablePort;

    @Transactional
    public TableEntity updateTable(Long id, UpdateTableRequest request) {
        TableEntity table = tablePort.getTableById(id);

        table.setName(request.getName());
        table.setType(TableTypeEnum.valueOf(request.getType()).name());
        table.setLocation(request.getLocation());
        table.setIsActive(request.getIsActive());

        return tablePort.save(table);
    }
}
