package hust.project.restaurant_management.usecase;

import hust.project.restaurant_management.entity.MenuSectionEntity;
import hust.project.restaurant_management.entity.dto.request.UpdateMenuSectionRequest;
import hust.project.restaurant_management.port.IMenuSectionPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateMenuSectionUseCase {
    private final IMenuSectionPort menuSectionPort;

    @Transactional
    public MenuSectionEntity updateMenuSection(Long id, UpdateMenuSectionRequest request) {
        MenuSectionEntity menuSection = menuSectionPort.getMenuSectionById(id);

        menuSection.setTitle(request.getTitle());
        menuSection.setDescription(request.getDescription());

        return menuSectionPort.save(menuSection);
    }
}
