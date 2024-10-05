package hust.project.restaurant_management.service.impl;

import hust.project.restaurant_management.entity.MenuSectionEntity;
import hust.project.restaurant_management.entity.dto.request.CreateMenuSectionRequest;
import hust.project.restaurant_management.entity.dto.request.UpdateMenuSectionRequest;
import hust.project.restaurant_management.service.IMenuSectionService;
import hust.project.restaurant_management.usercase.CreateMenuSectionUseCase;
import hust.project.restaurant_management.usercase.DeleteMenuSectionUseCase;
import hust.project.restaurant_management.usercase.GetMenuSectionUseCase;
import hust.project.restaurant_management.usercase.UpdateMenuSectionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuSectionService implements IMenuSectionService {
    private final CreateMenuSectionUseCase createMenuSectionUseCase;
    private final GetMenuSectionUseCase getMenuSectionUseCase;
    private final UpdateMenuSectionUseCase updateMenuSectionUseCase;
    private final DeleteMenuSectionUseCase deleteMenuSectionUseCase;

    @Override
    public MenuSectionEntity createMenuSection(CreateMenuSectionRequest request) {
        return createMenuSectionUseCase.createMenuSection(request);
    }

    @Override
    public List<MenuSectionEntity> getAll() {
        return getMenuSectionUseCase.getAll();
    }

    @Override
    public MenuSectionEntity getDetailMenuSection(Long id) {
        return getMenuSectionUseCase.getDetailMenuSection(id);
    }

    @Override
    public MenuSectionEntity updateMenuSection(Long id, UpdateMenuSectionRequest request) {
        return updateMenuSectionUseCase.updateMenuSection(id, request);
    }

    @Override
    public void deleteMenuSection(Long id) {
        deleteMenuSectionUseCase.deleteMenuSection(id);
    }
}
