package hust.project.restaurant_management.repository.adapter;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.entity.MenuSectionEntity;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.mapper.IMenuSectionMapper;
import hust.project.restaurant_management.model.MenuSectionModel;
import hust.project.restaurant_management.port.IMenuSectionPort;
import hust.project.restaurant_management.repository.IMenuSectionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MenuSectionAdapter implements IMenuSectionPort {
    private final IMenuSectionMapper menuSectionMapper;
    private final IMenuSectionRepository menuSectionRepository;

    @Override
    public MenuSectionEntity save(MenuSectionEntity menuSectionEntity) {
        try {
            MenuSectionModel menuSectionModel = menuSectionMapper.toModelFromEntity(menuSectionEntity);
            return menuSectionMapper.toEntityFromModel(menuSectionRepository.save(menuSectionModel));
        } catch (Exception e) {
            log.error("[MenuSectionAdapter] create menu section failed, {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_MENU_SECTION_FAILED);
        }
    }

    @Override
    public List<MenuSectionEntity> getAll() {
        return menuSectionMapper.toEntitiesFromModels(menuSectionRepository.findAll());
    }

    @Override
    public MenuSectionEntity getMenuSectionById(Long id) {
        return menuSectionMapper.toEntityFromModel(menuSectionRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.MENU_SECTION_NOT_FOUND)));
    }

    @Override
    public void deleteById(Long id) {
        try {
            menuSectionRepository.deleteById(id);
        } catch (Exception e) {
            throw new AppException(ErrorCode.DELETE_MENU_SECTION_FAILED);
        }
    }
}
