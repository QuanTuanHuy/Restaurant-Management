package hust.project.restaurant_management.repository;

import hust.project.restaurant_management.model.PaymentModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPaymentRepository extends IBaseRepository<PaymentModel> {
    List<PaymentModel> findByIdIn(List<Long> ids);
}
