package hust.project.restaurant_management.service.impl;

import hust.project.restaurant_management.entity.PaymentEntity;
import hust.project.restaurant_management.entity.dto.request.CreatePaymentRequest;
import hust.project.restaurant_management.service.IPaymentService;
import hust.project.restaurant_management.usecase.CreatePaymentUseCase;
import hust.project.restaurant_management.usecase.GetPaymentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService implements IPaymentService {
    private final CreatePaymentUseCase createPaymentUseCase;
    private final GetPaymentUseCase getPaymentUseCase;

    @Override
    public PaymentEntity createPayment(CreatePaymentRequest request) {
        return createPaymentUseCase.createPayment(request);
    }

    @Override
    public List<PaymentEntity> getPaymentsByIds(List<Long> ids) {
        return getPaymentUseCase.getPaymentsByIds(ids);
    }
}
