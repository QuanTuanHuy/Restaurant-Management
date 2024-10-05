package hust.project.restaurant_management.port;

import hust.project.restaurant_management.entity.MenuSectionEntity;

import java.util.List;

public interface IMenuSectionPort {
    MenuSectionEntity save(MenuSectionEntity menuSectionEntity);

    List<MenuSectionEntity> getAll();

    MenuSectionEntity getMenuSectionById(Long id);

    void deleteById(Long id);
}
