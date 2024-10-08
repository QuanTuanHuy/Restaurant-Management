package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.constants.GenderEnum;
import hust.project.restaurant_management.entity.CustomerEntity;
import hust.project.restaurant_management.entity.dto.request.UpdateCustomerRequest;
import hust.project.restaurant_management.port.ICustomerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateCustomerUseCase {
    private final ICustomerPort customerPort;

    @Transactional
    public CustomerEntity updateCustomer(Long id, UpdateCustomerRequest request) {
        CustomerEntity customer = customerPort.getCustomerById(id);

        customer.setName(request.getName());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setEmail(request.getEmail());
        customer.setAddress(request.getAddress());
        customer.setDob(request.getDob());
        customer.setGender(GenderEnum.valueOf(request.getGender()).name());

        return customerPort.save(customer);
    }
}
