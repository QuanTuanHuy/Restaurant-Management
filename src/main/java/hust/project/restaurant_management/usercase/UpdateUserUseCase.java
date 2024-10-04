package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.entity.UserEntity;
import hust.project.restaurant_management.entity.dto.request.UpdateUserRequest;
import hust.project.restaurant_management.mapper.IUserMapper;
import hust.project.restaurant_management.port.IRolePort;
import hust.project.restaurant_management.port.IUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserUseCase {
    private final IUserPort userPort;
    private final IUserMapper userMapper;
    private final IRolePort rolePort;

    public UserEntity updateUser(Long userId, UpdateUserRequest request) {
        userPort.getUserById(userId);
        rolePort.getRoleById(request.getRoleId());

        UserEntity user = userMapper.toEntityFromRequest(request);
        user.setId(userId);

        return userPort.save(user);
    }
}
