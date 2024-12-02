package hust.project.restaurant_management.repository.adapter;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.entity.PaymentEntity;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.mapper.IPaymentMapper;
import hust.project.restaurant_management.model.PaymentModel;
import hust.project.restaurant_management.port.IPaymentPort;
import hust.project.restaurant_management.repository.IPaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentAdapter implements IPaymentPort {
    private final IPaymentRepository paymentRepository;
    private final IPaymentMapper paymentMapper;

    @Override
    public PaymentEntity save(PaymentEntity paymentEntity) {
        try {
            PaymentModel paymentModel = paymentMapper.toModelFromEntity(paymentEntity);
            return paymentMapper.toEntityFromModel(paymentRepository.save(paymentModel));
        } catch (Exception e) {
            log.error("[PaymentAdapter] save payment failed: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_PAYMENT_FAILED);
        }
    }

    @Override
    public PaymentEntity getPaymentById(Long id) {
        return paymentMapper.toEntityFromModel(paymentRepository.findById(id).orElse(null));
    }

    @Override
    public List<PaymentEntity> getPaymentsByIds(List<Long> ids) {
        return paymentRepository.findByIdIn(ids).stream()
                .map(paymentMapper::toEntityFromModel)
                .toList();
    }
}
