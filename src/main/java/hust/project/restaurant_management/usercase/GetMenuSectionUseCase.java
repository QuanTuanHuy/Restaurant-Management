package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.entity.MenuItemEntity;
import hust.project.restaurant_management.entity.MenuSectionEntity;
import hust.project.restaurant_management.port.IMenuItemPort;
import hust.project.restaurant_management.port.IMenuSectionPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetMenuSectionUseCase {
    private final IMenuSectionPort menuSectionPort;
    private final IMenuItemPort menuItemPort;

    public List<MenuSectionEntity> getAll() {
        List<MenuSectionEntity> menuSections = menuSectionPort.getAll();

        List<MenuItemEntity> menuItems = menuItemPort.getAllMenuItems();

        menuSections.forEach(menuSection -> {
            List<MenuItemEntity> items = menuItems.stream()
                    .filter(menuItem -> menuItem.getMenuSectionId().equals(menuSection.getId()))
                    .toList();
            menuSection.setMenuItems(items);
        });

        return menuSections;
    }

    public MenuSectionEntity getDetailMenuSection(Long id) {
        MenuSectionEntity menuSection = menuSectionPort.getMenuSectionById(id);

        List<MenuItemEntity> menuItems = menuItemPort.getMenuItemsByMenuSectionId(id);
        menuSection.setMenuItems(menuItems);

        return menuSection;
    }
}
