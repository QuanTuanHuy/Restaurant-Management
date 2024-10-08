package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.entity.CustomerEntity;
import hust.project.restaurant_management.entity.dto.request.GetCustomerRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.port.ICustomerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCustomerUseCase {
    private final ICustomerPort customerPort;

    public Pair<PageInfo, List<CustomerEntity>> getAllCustomers(GetCustomerRequest filter) {
        return customerPort.getAllCustomers(filter);
    }

    public CustomerEntity getCustomerDetail(Long id) {
        return customerPort.getCustomerById(id);
    }
}
