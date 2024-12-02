package hust.project.restaurant_management.port;

import hust.project.restaurant_management.entity.PaymentEntity;

import java.util.List;

public interface IPaymentPort {
    PaymentEntity save(PaymentEntity paymentEntity);

    PaymentEntity getPaymentById(Long id);

    List<PaymentEntity> getPaymentsByIds(List<Long> ids);
}
