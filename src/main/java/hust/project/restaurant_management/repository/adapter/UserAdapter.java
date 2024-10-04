package hust.project.restaurant_management.repository.adapter;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.entity.UserEntity;
import hust.project.restaurant_management.entity.dto.request.GetUserRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.mapper.IUserMapper;
import hust.project.restaurant_management.model.UserModel;
import hust.project.restaurant_management.port.IUserPort;
import hust.project.restaurant_management.repository.IUserRepository;
import hust.project.restaurant_management.repository.specification.UserSpecification;
import hust.project.restaurant_management.utils.PageInfoUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAdapter implements IUserPort {
    private final IUserRepository userRepository;
    private final IUserMapper userMapper;

    @Override
    public UserEntity save(UserEntity userEntity) {
        try {
            UserModel userModel = userMapper.toModelFromEntity(userEntity);
            return userMapper.toEntityFromModel(userRepository.save(userModel));
        } catch (Exception e) {
            log.error("[UserAdapter] save user failed: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_USER_FAILED);
        }
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return userMapper.toEntityFromModel(userRepository.findByEmail(email).orElse(null));
    }

    @Override
    public UserEntity getUserById(Long id) {
        return userMapper.toEntityFromModel(userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    @Override
    public Pair<PageInfo, List<UserEntity>> getAllUsers(GetUserRequest filter) {
        Pageable pageable = PageRequest.of(Math.toIntExact(filter.getPage()),
                Math.toIntExact(filter.getPageSize()), Sort.by("id").descending());

        var userPage = userRepository.findAll(UserSpecification.getAll(filter), pageable);

        var pageInfo = PageInfoUtils.getPageInfo(userPage);

        return Pair.of(pageInfo, userMapper.toEntitiesFromModels(userPage.getContent()));
    }
}
