package hust.project.restaurant_management.usecase;

import hust.project.restaurant_management.entity.MenuItemEntity;
import hust.project.restaurant_management.entity.MenuSectionEntity;
import hust.project.restaurant_management.entity.dto.request.GetMenuItemRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.port.IMenuItemPort;
import hust.project.restaurant_management.port.IMenuSectionPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetMenuItemUseCase {
    private final IMenuItemPort menuItemPort;
    private final IMenuSectionPort menuSectionPort;

    public Pair<PageInfo, List<MenuItemEntity>> getAllMenuItems(GetMenuItemRequest filter) {
        return menuItemPort.getAllMenuItems(filter);
    }

    public MenuItemEntity getDetailMenuItem(Long id) {
        MenuItemEntity menuItem = menuItemPort.getMenuItemById(id);
        MenuSectionEntity menuSection = menuSectionPort.getMenuSectionById(menuItem.getMenuSectionId());
        menuItem.setMenuSection(menuSection);
        return menuItem;
    }
}
