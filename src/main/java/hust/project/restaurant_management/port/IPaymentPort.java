package hust.project.restaurant_management.port;

import hust.project.restaurant_management.entity.PaymentEntity;

public interface IPaymentPort {
    PaymentEntity save(PaymentEntity paymentEntity);

    PaymentEntity getPaymentById(Long id);
}
