package hust.project.restaurant_management.service;

import hust.project.restaurant_management.entity.MenuSectionEntity;
import hust.project.restaurant_management.entity.dto.request.CreateMenuSectionRequest;
import hust.project.restaurant_management.entity.dto.request.UpdateMenuSectionRequest;

import java.util.List;

public interface IMenuSectionService {
    MenuSectionEntity createMenuSection(CreateMenuSectionRequest request);

    List<MenuSectionEntity> getAll();

    MenuSectionEntity getDetailMenuSection(Long id);

    MenuSectionEntity updateMenuSection(Long id, UpdateMenuSectionRequest request);

    void deleteMenuSection(Long id);
}
