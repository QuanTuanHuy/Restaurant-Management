package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.entity.RoleEntity;
import hust.project.restaurant_management.entity.UserEntity;
import hust.project.restaurant_management.entity.dto.request.GetUserRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.port.IRolePort;
import hust.project.restaurant_management.port.IUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetUserUseCase {

    private final IUserPort userPort;
    private final IRolePort rolePort;

    public UserEntity getDetailUser(Long userId) {
        UserEntity user = userPort.getUserById(userId);
        RoleEntity role = rolePort.getRoleById(user.getRoleId());

        user.setRole(role);
        return user;
    }


    public Pair<PageInfo, List<UserEntity>> getAll(GetUserRequest filter) {
        var result = userPort.getAllUsers(filter);

        List<RoleEntity> roles = rolePort.getAllRoles();
        result.getSecond().forEach(user -> {
            RoleEntity role = roles.stream().filter(r -> r.getId().equals(user.getRoleId())).findFirst().orElse(null);
            user.setRole(role);
        });

        return result;
    }
}
