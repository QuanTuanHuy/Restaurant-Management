package hust.project.restaurant_management.service;


import hust.project.restaurant_management.entity.RoleEntity;
import hust.project.restaurant_management.entity.dto.request.CreateRoleRequest;

import java.util.List;

public interface IRoleService {
    RoleEntity createRole(CreateRoleRequest request);

    RoleEntity getDetailRole(Long id);

    List<RoleEntity> getAllRoles();

    void deleteRole(Long id);
}
