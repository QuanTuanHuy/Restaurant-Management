package hust.project.restaurant_management.port;

import hust.project.restaurant_management.entity.CustomerEntity;
import hust.project.restaurant_management.entity.dto.request.GetCustomerRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface ICustomerPort {
    CustomerEntity save(CustomerEntity customerEntity);

    Pair<PageInfo, List<CustomerEntity>> getAllCustomers(GetCustomerRequest filter);

    CustomerEntity getCustomerById(Long id);

    void deleteCustomerById(Long id);
}
