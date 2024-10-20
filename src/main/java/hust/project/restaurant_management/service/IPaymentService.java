package hust.project.restaurant_management.service;

import hust.project.restaurant_management.entity.PaymentEntity;
import hust.project.restaurant_management.entity.dto.request.CreatePaymentRequest;

public interface IPaymentService {
    PaymentEntity createPayment(CreatePaymentRequest request);
}
