package hust.project.restaurant_management.service.impl;

import hust.project.restaurant_management.entity.MenuItemEntity;
import hust.project.restaurant_management.entity.dto.request.CreateMenuItemRequest;
import hust.project.restaurant_management.entity.dto.request.GetMenuItemRequest;
import hust.project.restaurant_management.entity.dto.request.UpdateMenuItemRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.service.IMenuItemService;
import hust.project.restaurant_management.usercase.CreateMenuItemUseCase;
import hust.project.restaurant_management.usercase.DeleteMenuItemUseCase;
import hust.project.restaurant_management.usercase.GetMenuItemUseCase;
import hust.project.restaurant_management.usercase.UpdateMenuItemUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemService implements IMenuItemService {
    private final CreateMenuItemUseCase createMenuItemUseCase;
    private final GetMenuItemUseCase getMenuItemUseCase;
    private final UpdateMenuItemUseCase updateMenuItemUseCase;
    private final DeleteMenuItemUseCase deleteMenuItemUseCase;

    @Override
    public MenuItemEntity createMenuItem(CreateMenuItemRequest request) {
        return createMenuItemUseCase.createMenuItem(request);
    }

    @Override
    public Pair<PageInfo, List<MenuItemEntity>> getAllMenuItems(GetMenuItemRequest filter) {
        return getMenuItemUseCase.getAllMenuItems(filter);
    }

    @Override
    public MenuItemEntity getDetailMenuItem(Long id) {
        return getMenuItemUseCase.getDetailMenuItem(id);
    }

    @Override
    public MenuItemEntity updateMenuItem(Long id, UpdateMenuItemRequest request) {
        return updateMenuItemUseCase.updateMenuItem(id, request);
    }

    @Override
    public void deleteMenuItem(Long id) {
        deleteMenuItemUseCase.deleteMenuItem(id);
    }
}
