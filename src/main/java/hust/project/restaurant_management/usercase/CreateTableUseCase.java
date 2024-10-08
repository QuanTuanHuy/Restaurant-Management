package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.constants.TableTypeEnum;
import hust.project.restaurant_management.entity.TableEntity;
import hust.project.restaurant_management.entity.dto.request.CreateTableRequest;
import hust.project.restaurant_management.mapper.ITableMapper;
import hust.project.restaurant_management.port.ITablePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateTableUseCase {
    private final ITableMapper tableMapper;
    private final ITablePort tablePort;

    @Transactional
    public TableEntity createTable(CreateTableRequest request) {
        TableEntity table = tableMapper.toEntityFromRequest(request);
        table.setType(TableTypeEnum.valueOf(request.getType()).name());
        return tablePort.save(table);
    }
}
