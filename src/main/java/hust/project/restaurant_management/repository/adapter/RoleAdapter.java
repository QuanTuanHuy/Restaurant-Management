package hust.project.restaurant_management.repository.adapter;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.entity.RoleEntity;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.mapper.IRoleMapper;
import hust.project.restaurant_management.model.RoleModel;
import hust.project.restaurant_management.port.IRolePort;
import hust.project.restaurant_management.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleAdapter implements IRolePort {
    private final IRoleRepository roleRepository;
    private final IRoleMapper roleMapper;

    @Override
    public RoleEntity save(RoleEntity roleEntity) {
        try {
            RoleModel roleModel = roleMapper.toModelFromEntity(roleEntity);
            return roleMapper.toEntityFromModel(roleRepository.save(roleModel));
        } catch (Exception e) {
            log.error("[RoleAdapter] save role failed: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_ROLE_FAILED);
        }
    }

    @Override
    public RoleEntity getRoleById(Long id) {
        return roleMapper.toEntityFromModel(roleRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND)));
    }

    @Override
    public List<RoleEntity> getAllRoles() {
        return roleMapper.toEntitiesFromModels(roleRepository.findAll());
    }

    @Override
    public void deleteRoleById(Long id) {
        try {
            roleRepository.deleteById(id);
        } catch (Exception e) {
            log.error("[RoleAdapter] delete role failed: {}", e.getMessage());
            throw new AppException(ErrorCode.ROLE_NOT_FOUND);
        }
    }
}
