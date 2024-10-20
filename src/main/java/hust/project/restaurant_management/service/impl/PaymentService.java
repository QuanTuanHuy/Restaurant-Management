package hust.project.restaurant_management.service.impl;

import hust.project.restaurant_management.entity.PaymentEntity;
import hust.project.restaurant_management.entity.dto.request.CreatePaymentRequest;
import hust.project.restaurant_management.service.IPaymentService;
import hust.project.restaurant_management.usercase.CreatePaymentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService implements IPaymentService {
    private final CreatePaymentUseCase createPaymentUseCase;

    @Override
    public PaymentEntity createPayment(CreatePaymentRequest request) {
        return createPaymentUseCase.createPayment(request);
    }
}
