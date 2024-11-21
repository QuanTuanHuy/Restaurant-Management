package hust.project.restaurant_management.service.impl;

import hust.project.restaurant_management.entity.RoleEntity;
import hust.project.restaurant_management.entity.dto.request.CreateRoleRequest;
import hust.project.restaurant_management.service.IRoleService;
import hust.project.restaurant_management.usecase.CreateRoleUseCase;
import hust.project.restaurant_management.usecase.DeleteRoleUseCase;
import hust.project.restaurant_management.usecase.GetRoleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {
    private final CreateRoleUseCase createRoleUseCase;
    private final GetRoleUseCase getRoleUseCase;
    private final DeleteRoleUseCase deleteRoleUseCase;

    @Override
    public RoleEntity createRole(CreateRoleRequest request) {
        return createRoleUseCase.createRole(request);
    }

    @Override
    public RoleEntity getDetailRole(Long id) {
        return getRoleUseCase.getDetailRole(id);
    }

    @Override
    public List<RoleEntity> getAllRoles() {
        return getRoleUseCase.getAllRoles();
    }

    @Override
    public void deleteRole(Long id) {
        deleteRoleUseCase.deleteRole(id);
    }
}
