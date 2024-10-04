package hust.project.restaurant_management.service;

import hust.project.restaurant_management.entity.UserEntity;
import hust.project.restaurant_management.entity.dto.request.CreateUserRequest;
import hust.project.restaurant_management.entity.dto.request.GetUserRequest;
import hust.project.restaurant_management.entity.dto.request.UpdateUserRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IUserService {
    UserEntity createUser(CreateUserRequest request);

    UserEntity getDetailUser(Long userId);

    Pair<PageInfo, List<UserEntity>> getAll(GetUserRequest filter);

    UserEntity updateUser(Long userId, UpdateUserRequest request);
}
