package hust.project.restaurant_management.service;

import hust.project.restaurant_management.entity.PaymentEntity;
import hust.project.restaurant_management.entity.dto.request.CreatePaymentRequest;

import java.util.List;

public interface IPaymentService {
    PaymentEntity createPayment(CreatePaymentRequest request);

    List<PaymentEntity> getPaymentsByIds(List<Long> ids);
}
