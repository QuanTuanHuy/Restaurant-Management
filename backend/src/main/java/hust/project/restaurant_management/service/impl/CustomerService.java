package hust.project.restaurant_management.service.impl;

import hust.project.restaurant_management.entity.CustomerEntity;
import hust.project.restaurant_management.entity.dto.request.CreateCustomerRequest;
import hust.project.restaurant_management.entity.dto.request.GetCustomerRequest;
import hust.project.restaurant_management.entity.dto.request.UpdateCustomerRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.service.ICustomerService;
import hust.project.restaurant_management.usecase.CreateCustomerUseCase;
import hust.project.restaurant_management.usecase.DeleteCustomerUseCase;
import hust.project.restaurant_management.usecase.GetCustomerUseCase;
import hust.project.restaurant_management.usecase.UpdateCustomerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {
    private final CreateCustomerUseCase createCustomerUseCase;
    private final GetCustomerUseCase getCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;
    @Override
    public CustomerEntity createCustomer(CreateCustomerRequest request) {
        return createCustomerUseCase.createCustomer(request);
    }

    @Override
    public Pair<PageInfo, List<CustomerEntity>> getAllCustomers(GetCustomerRequest filter) {
        return getCustomerUseCase.getAllCustomers(filter);
    }

    @Override
    public CustomerEntity getCustomerDetail(Long id) {
        return getCustomerUseCase.getCustomerDetail(id);
    }

    @Override
    public CustomerEntity updateCustomer(Long id, UpdateCustomerRequest request) {
        return updateCustomerUseCase.updateCustomer(id, request);
    }

    @Override
    public void deleteCustomer(Long id) {
        deleteCustomerUseCase.deleteCustomer(id);
    }
}
