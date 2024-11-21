package hust.project.restaurant_management.usecase;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.entity.MenuItemEntity;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.port.IMenuItemPort;
import hust.project.restaurant_management.port.IMenuSectionPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DeleteMenuSectionUseCase {
    private final IMenuSectionPort menuSectionPort;
    private final IMenuItemPort menuItemPort;

    public void deleteMenuSection(Long id) {
        List<MenuItemEntity> menuItems = menuItemPort.getMenuItemsByMenuSectionId(id);
        if (!CollectionUtils.isEmpty(menuItems)) {
            log.error("[DeleteMenuSectionUseCase] delete menu section failed, menu items exist");
            throw new AppException(ErrorCode.DELETE_MENU_SECTION_FAILED);
        }

        menuSectionPort.deleteById(id);
    }
}
