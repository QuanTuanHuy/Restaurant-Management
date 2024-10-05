package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.entity.MenuItemEntity;
import hust.project.restaurant_management.entity.dto.request.UpdateMenuItemRequest;
import hust.project.restaurant_management.port.IMenuItemPort;
import hust.project.restaurant_management.port.IMenuSectionPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateMenuItemUseCase {
    private final IMenuItemPort menuItemPort;
    private final IMenuSectionPort menuSectionPort;

    @Transactional
    public MenuItemEntity updateMenuItem(Long id, UpdateMenuItemRequest request) {
        MenuItemEntity menuItem = menuItemPort.getMenuItemById(id);
        menuSectionPort.getMenuSectionById(request.getMenuSectionId());

        menuItem.setMenuSectionId(request.getMenuSectionId());
        menuItem.setTitle(request.getTitle());
        menuItem.setDescription(request.getDescription());
        menuItem.setCostPrice(request.getCostPrice());
        menuItem.setSellingPrice(request.getSellingPrice());
        menuItem.setThumbnailImg(request.getThumbnailImg());

        return menuItemPort.save(menuItem);
    }
}
