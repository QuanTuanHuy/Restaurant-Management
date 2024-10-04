package hust.project.restaurant_management.repository;

import hust.project.restaurant_management.model.UserModel;

import java.util.Optional;

public interface IUserRepository extends IBaseRepository<UserModel> {
    Optional<UserModel> findByEmail(String email);
}
