package hust.project.restaurant_management.usecase;

import hust.project.restaurant_management.entity.PaymentEntity;
import hust.project.restaurant_management.port.IPaymentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetPaymentUseCase {
    private final IPaymentPort paymentPort;

    public List<PaymentEntity> getPaymentsByIds(List<Long> ids) {
        return paymentPort.getPaymentsByIds(ids);
    }
}
