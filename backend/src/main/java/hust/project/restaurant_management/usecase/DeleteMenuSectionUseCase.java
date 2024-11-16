package hust.project.restaurant_management.usecase;

import hust.project.restaurant_management.port.IMenuSectionPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteMenuSectionUseCase {
    private final IMenuSectionPort menuSectionPort;

    public void deleteMenuSection(Long id) {
        menuSectionPort.deleteById(id);
    }
}
