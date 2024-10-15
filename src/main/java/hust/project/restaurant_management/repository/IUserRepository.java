package hust.project.restaurant_management.repository;

import hust.project.restaurant_management.model.UserModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends IBaseRepository<UserModel> {
    Optional<UserModel> findByEmail(String email);

    List<UserModel> findByIdIn(List<Long> ids);
}
