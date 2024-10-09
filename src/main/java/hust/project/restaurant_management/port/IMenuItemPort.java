package hust.project.restaurant_management.port;

import hust.project.restaurant_management.entity.MenuItemEntity;
import hust.project.restaurant_management.entity.dto.request.GetMenuItemRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IMenuItemPort {
    MenuItemEntity save(MenuItemEntity menuItemEntity);

    Pair<PageInfo, List<MenuItemEntity>> getAllMenuItems(GetMenuItemRequest filter);

    List<MenuItemEntity> getAllMenuItems();

    List<MenuItemEntity> getMenuItemsByIds(List<Long> ids);

    List<MenuItemEntity> getMenuItemsByMenuSectionId(Long menuSectionId);

    MenuItemEntity getMenuItemById(Long id);

    void deleteMenuItemById(Long id);
}
