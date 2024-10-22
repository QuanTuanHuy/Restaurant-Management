package hust.project.restaurant_management.repository.adapter;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.entity.MenuItemEntity;
import hust.project.restaurant_management.entity.dto.request.GetMenuItemRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.mapper.IMenuItemMapper;
import hust.project.restaurant_management.model.MenuItemModel;
import hust.project.restaurant_management.port.IMenuItemPort;
import hust.project.restaurant_management.repository.IMenuItemRepository;
import hust.project.restaurant_management.repository.specification.MenuItemSpecification;
import hust.project.restaurant_management.utils.PageInfoUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MenuItemAdapter implements IMenuItemPort {
    private final IMenuItemMapper menuItemMapper;
    private final IMenuItemRepository menuItemRepository;

    @Override
    public MenuItemEntity save(MenuItemEntity menuItemEntity) {
        try {
            MenuItemModel menuItemModel = menuItemMapper.toModelFromEntity(menuItemEntity);
            return menuItemMapper.toEntityFromModel(menuItemRepository.save(menuItemModel));
        } catch (Exception e) {
            log.error("[MenuItemAdapter] save menu item failed, {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_MENU_ITEM_FAIL);
        }
    }

    @Override
    public Pair<PageInfo, List<MenuItemEntity>> getAllMenuItems(GetMenuItemRequest filter) {
        Pageable pageable = PageRequest.of(Math.toIntExact(filter.getPage()), Math.toIntExact(filter.getPageSize()),
                Sort.by("id").descending());

        var result = menuItemRepository.findAll(MenuItemSpecification.getAll(filter), pageable);

        var pageInfo = PageInfoUtils.getPageInfo(result);

        return Pair.of(pageInfo, menuItemMapper.toEntitiesFromModels(result.getContent()));
    }

    @Override
    public List<MenuItemEntity> getAllMenuItems() {
        return menuItemMapper.toEntitiesFromModels(menuItemRepository.findAll());
    }

    @Override
    public List<MenuItemEntity> getMenuItemsByIds(List<Long> ids) {
        return menuItemMapper.toEntitiesFromModels(menuItemRepository.findByIdIn(ids));
    }

    @Override
    public List<MenuItemEntity> getMenuItemsByMenuSectionId(Long menuSectionId) {
        return menuItemMapper.toEntitiesFromModels(menuItemRepository.findByMenuSectionId(menuSectionId));
    }

    @Override
    public MenuItemEntity getMenuItemById(Long id) {
        return menuItemMapper.toEntityFromModel(menuItemRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.MENU_ITEM_NOT_FOUND)));
    }

    @Override
    public void deleteMenuItemById(Long id) {
        try {
            menuItemRepository.deleteById(id);
        } catch (Exception e) {
            throw new AppException(ErrorCode.DELETE_MENU_ITEM_FAILED);
        }
    }
}
