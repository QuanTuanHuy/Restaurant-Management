package hust.project.restaurant_management.usecase;

import hust.project.restaurant_management.entity.RoleEntity;
import hust.project.restaurant_management.port.IRolePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetRoleUseCase {
    private final IRolePort rolePort;

    public RoleEntity getDetailRole(Long id) {
        return rolePort.getRoleById(id);
    }

    public List<RoleEntity> getAllRoles() {
        return rolePort.getAllRoles();
    }
}
