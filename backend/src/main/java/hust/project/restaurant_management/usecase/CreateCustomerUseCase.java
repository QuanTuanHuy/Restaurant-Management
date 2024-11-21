package hust.project.restaurant_management.usecase;

import hust.project.restaurant_management.constants.GenderEnum;
import hust.project.restaurant_management.entity.CustomerEntity;
import hust.project.restaurant_management.entity.dto.request.CreateCustomerRequest;
import hust.project.restaurant_management.mapper.ICustomerMapper;
import hust.project.restaurant_management.port.ICustomerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateCustomerUseCase {
    private final ICustomerPort customerPort;
    private final ICustomerMapper customerMapper;

    @Transactional
    public CustomerEntity createCustomer(CreateCustomerRequest request) {
        CustomerEntity customer = customerMapper.toEntityFromRequest(request);
        customer.setGender(GenderEnum.valueOf(request.getGender()).name());
        return customerPort.save(customer);
    }
}
