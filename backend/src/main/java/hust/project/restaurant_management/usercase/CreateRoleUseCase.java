package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.constants.RoleEnum;
import hust.project.restaurant_management.entity.RoleEntity;
import hust.project.restaurant_management.entity.dto.request.CreateRoleRequest;
import hust.project.restaurant_management.mapper.IRoleMapper;
import hust.project.restaurant_management.port.IRolePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateRoleUseCase {
    private final IRoleMapper roleMapper;
    private final IRolePort rolePort;

    public RoleEntity createRole(CreateRoleRequest request) {
        RoleEntity role = roleMapper.toEntityFromRequest(request);
        role.setName(RoleEnum.valueOf(request.getName()).name());
        return rolePort.save(role);
    }
}
