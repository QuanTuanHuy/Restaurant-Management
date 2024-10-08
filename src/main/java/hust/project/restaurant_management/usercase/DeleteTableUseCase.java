package hust.project.restaurant_management.usercase;

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
        tablePort.deleteTableById(id);
    }
}
