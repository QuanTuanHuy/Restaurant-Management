package hust.project.restaurant_management.service;

import hust.project.restaurant_management.entity.MenuItemEntity;
import hust.project.restaurant_management.entity.dto.request.CreateMenuItemRequest;
import hust.project.restaurant_management.entity.dto.request.GetMenuItemRequest;
import hust.project.restaurant_management.entity.dto.request.UpdateMenuItemRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IMenuItemService {
    MenuItemEntity createMenuItem(CreateMenuItemRequest request);

    Pair<PageInfo, List<MenuItemEntity>> getAllMenuItems(GetMenuItemRequest filter);

    MenuItemEntity getDetailMenuItem(Long id);

    MenuItemEntity updateMenuItem(Long id, UpdateMenuItemRequest request);

    void deleteMenuItem(Long id);
}
