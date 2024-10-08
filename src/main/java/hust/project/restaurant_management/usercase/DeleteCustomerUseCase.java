package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.port.ICustomerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteCustomerUseCase {
    private final ICustomerPort customerPort;

    @Transactional
    public void deleteCustomer(Long id) {
        customerPort.deleteCustomerById(id);
    }
}
