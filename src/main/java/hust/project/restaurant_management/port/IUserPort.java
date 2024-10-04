package hust.project.restaurant_management.port;

import hust.project.restaurant_management.entity.UserEntity;
import hust.project.restaurant_management.entity.dto.request.GetUserRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IUserPort {
    UserEntity save(UserEntity userEntity);

    UserEntity getUserByEmail(String email);

    UserEntity getUserById(Long id);

    Pair<PageInfo, List<UserEntity>> getAllUsers(GetUserRequest filter);
}
