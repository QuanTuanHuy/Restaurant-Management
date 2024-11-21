package hust.project.restaurant_management.usecase;

import hust.project.restaurant_management.entity.MenuSectionEntity;
import hust.project.restaurant_management.entity.dto.request.CreateMenuSectionRequest;
import hust.project.restaurant_management.mapper.IMenuSectionMapper;
import hust.project.restaurant_management.port.IMenuSectionPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateMenuSectionUseCase {
    private final IMenuSectionPort menuSectionPort;
    private final IMenuSectionMapper menuSectionMapper;

    public MenuSectionEntity createMenuSection(CreateMenuSectionRequest request) {
        MenuSectionEntity menuSection = menuSectionMapper.toEntityFromRequest(request);
        return menuSectionPort.save(menuSection);
    }
}
