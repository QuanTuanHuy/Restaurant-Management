package hust.project.restaurant_management.service.impl;

import hust.project.restaurant_management.entity.UserEntity;
import hust.project.restaurant_management.entity.dto.request.CreateUserRequest;
import hust.project.restaurant_management.entity.dto.request.GetUserRequest;
import hust.project.restaurant_management.entity.dto.request.UpdateUserRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.service.IUserService;
import hust.project.restaurant_management.usecase.CreateUserUseCase;
import hust.project.restaurant_management.usecase.GetUserUseCase;
import hust.project.restaurant_management.usecase.UpdateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final CreateUserUseCase createUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;

    @Override
    public UserEntity createUser(CreateUserRequest request) {
        return createUserUseCase.createUser(request);
    }

    @Override
    public UserEntity getDetailUser(Long userId) {
        return getUserUseCase.getDetailUser(userId);
    }

    @Override
    public Pair<PageInfo, List<UserEntity>> getAll(GetUserRequest filter) {
        return getUserUseCase.getAll(filter);
    }

    @Override
    public UserEntity updateUser(Long userId, UpdateUserRequest request) {
        return updateUserUseCase.updateUser(userId, request);
    }
}
