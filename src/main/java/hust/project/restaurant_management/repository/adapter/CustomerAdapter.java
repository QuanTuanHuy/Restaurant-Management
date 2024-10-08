package hust.project.restaurant_management.repository.adapter;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.entity.CustomerEntity;
import hust.project.restaurant_management.entity.dto.request.GetCustomerRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.mapper.ICustomerMapper;
import hust.project.restaurant_management.model.CustomerModel;
import hust.project.restaurant_management.port.ICustomerPort;
import hust.project.restaurant_management.repository.ICustomerRepository;
import hust.project.restaurant_management.repository.specification.CustomerSpecification;
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
public class CustomerAdapter implements ICustomerPort {
    private final ICustomerMapper customerMapper;
    private final ICustomerRepository customerRepository;

    @Override
    public CustomerEntity save(CustomerEntity customerEntity) {
        try {
            CustomerModel customerModel = customerMapper.toModelFromEntity(customerEntity);
            return customerMapper.toEntityFromModel(customerRepository.save(customerModel));
        } catch (Exception e) {
            log.error("[CustomerAdapter] save customer failed: error: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_CUSTOMER_FAILED);
        }
    }

    @Override
    public Pair<PageInfo, List<CustomerEntity>> getAllCustomers(GetCustomerRequest filter) {
        Pageable pageable = PageRequest.of(Math.toIntExact(filter.getPage()), Math.toIntExact(filter.getPageSize()),
                Sort.by("name").ascending());

        var result = customerRepository.findAll(CustomerSpecification.getAllCustomers(filter), pageable);

        var pageInfo = PageInfoUtils.getPageInfo(result);

        return Pair.of(pageInfo, customerMapper.toEntitiesFromModels(result.getContent()));
    }

    @Override
    public CustomerEntity getCustomerById(Long id) {
        return customerMapper.toEntityFromModel(customerRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_FOUND)));
    }

    @Override
    public void deleteCustomerById(Long id) {
        try {
            customerRepository.deleteById(id);
        } catch (Exception e) {
            log.error("[CustomerAdapter] delete customer failed: error: {}", e.getMessage());
            throw new AppException(ErrorCode.DELETE_CUSTOMER_FAILED);
        }
    }
}
