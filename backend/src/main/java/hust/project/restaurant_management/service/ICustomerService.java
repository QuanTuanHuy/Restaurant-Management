package hust.project.restaurant_management.service;

import hust.project.restaurant_management.entity.CustomerEntity;
import hust.project.restaurant_management.entity.dto.request.CreateCustomerRequest;
import hust.project.restaurant_management.entity.dto.request.GetCustomerRequest;
import hust.project.restaurant_management.entity.dto.request.UpdateCustomerRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface ICustomerService {
    CustomerEntity createCustomer(CreateCustomerRequest request);

    Pair<PageInfo, List<CustomerEntity>> getAllCustomers(GetCustomerRequest filter);

    CustomerEntity getCustomerDetail(Long id);

    CustomerEntity updateCustomer(Long id, UpdateCustomerRequest request);

    void deleteCustomer(Long id);
}
