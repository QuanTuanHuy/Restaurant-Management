package hust.project.restaurant_management.repository;

import hust.project.restaurant_management.model.MenuItemModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMenuItemRepository extends IBaseRepository<MenuItemModel> {
    List<MenuItemModel> findByMenuSectionId(Long menuSectionId);

    List<MenuItemModel> findByIdIn(List<Long> ids);
}
