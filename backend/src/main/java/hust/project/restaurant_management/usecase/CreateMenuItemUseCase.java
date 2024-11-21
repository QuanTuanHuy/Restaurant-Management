package hust.project.restaurant_management.usecase;

import hust.project.restaurant_management.entity.MenuItemEntity;
import hust.project.restaurant_management.entity.dto.request.CreateMenuItemRequest;
import hust.project.restaurant_management.mapper.IMenuItemMapper;
import hust.project.restaurant_management.port.IMenuItemPort;
import hust.project.restaurant_management.port.IMenuSectionPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateMenuItemUseCase {
    private final IMenuItemPort menuItemPort;
    private final IMenuSectionPort menuSectionPort;
    private final IMenuItemMapper menuItemMapper;

    public MenuItemEntity createMenuItem(CreateMenuItemRequest request) {
        menuSectionPort.getMenuSectionById(request.getMenuSectionId());

        MenuItemEntity menuItem = menuItemMapper.toEntityFromRequest(request);

        return menuItemPort.save(menuItem);
    }
}
