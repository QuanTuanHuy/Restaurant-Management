package hust.project.restaurant_management.mapper;

import hust.project.restaurant_management.entity.CustomerEntity;
import hust.project.restaurant_management.entity.dto.request.CreateCustomerRequest;
import hust.project.restaurant_management.model.CustomerModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICustomerMapper {
    CustomerEntity toEntityFromModel(CustomerModel model);

    CustomerModel toModelFromEntity(CustomerEntity entity);

    List<CustomerEntity> toEntitiesFromModels(List<CustomerModel> models);

    CustomerEntity toEntityFromRequest(CreateCustomerRequest request);
}
