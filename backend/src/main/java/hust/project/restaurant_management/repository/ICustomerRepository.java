package hust.project.restaurant_management.repository;

import hust.project.restaurant_management.model.CustomerModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerRepository extends IBaseRepository<CustomerModel> {
    List<CustomerModel> findByIdIn(List<Long> ids);
}
