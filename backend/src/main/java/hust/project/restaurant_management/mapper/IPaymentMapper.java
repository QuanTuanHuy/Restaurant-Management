package hust.project.restaurant_management.mapper;

import hust.project.restaurant_management.entity.PaymentEntity;
import hust.project.restaurant_management.entity.dto.request.CreatePaymentRequest;
import hust.project.restaurant_management.model.PaymentModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IPaymentMapper {
    PaymentEntity toEntityFromModel(PaymentModel model);

    PaymentModel toModelFromEntity(PaymentEntity entity);

    @Mapping(target = "paymentMethod", ignore = true)
    PaymentEntity toEntityFromRequest(CreatePaymentRequest request);
}
