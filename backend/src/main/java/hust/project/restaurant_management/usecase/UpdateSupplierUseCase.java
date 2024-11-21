package hust.project.restaurant_management.usecase;

import hust.project.restaurant_management.constants.SupplierStatusEnum;
import hust.project.restaurant_management.entity.SupplierEntity;
import hust.project.restaurant_management.entity.dto.request.UpdateSupplierRequest;
import hust.project.restaurant_management.port.ISupplierPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateSupplierUseCase {
    private final ISupplierPort supplierPort;

    @Transactional
    public SupplierEntity updateSupplier(Long id, UpdateSupplierRequest request) {
        SupplierEntity supplier = supplierPort.getSupplierById(id);

        supplier.setName(request.getName());
        supplier.setCode(request.getCode());
        supplier.setEmail(request.getEmail());
        supplier.setPhoneNumber(request.getPhoneNumber());
        supplier.setAddress(request.getAddress());
        supplier.setStatus(SupplierStatusEnum.valueOf(request.getStatus()).name());

        return supplierPort.save(supplier);
    }
}
