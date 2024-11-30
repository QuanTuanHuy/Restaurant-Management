package hust.project.restaurant_management.usecase;

import hust.project.restaurant_management.entity.TableEntity;
import hust.project.restaurant_management.port.ITablePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteTableUseCase {
    private final ITablePort tablePort;

    @Transactional
    public void deleteTable(Long id) {
        TableEntity table = tablePort.getTableById(id);
        table.setIsActive(false);
        tablePort.save(table);
    }
}
