package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.port.IMenuItemPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteMenuItemUseCase {
    private final IMenuItemPort menuItemPort;

    public void deleteMenuItem(Long id) {
        menuItemPort.deleteMenuItemById(id);
    }
}
