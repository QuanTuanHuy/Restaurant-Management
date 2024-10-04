package hust.project.restaurant_management.port;

import hust.project.restaurant_management.entity.RoleEntity;

import java.util.List;

public interface IRolePort {
    RoleEntity save(RoleEntity roleEntity);

    RoleEntity getRoleById(Long id);

    List<RoleEntity> getAllRoles();

    void deleteRoleById(Long id);
}
