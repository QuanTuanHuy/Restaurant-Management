package hust.project.restaurant_management.usecase;

import hust.project.restaurant_management.entity.SupplierEntity;
import hust.project.restaurant_management.entity.dto.request.CreateSupplierRequest;
import hust.project.restaurant_management.mapper.ISupplierMapper;
import hust.project.restaurant_management.port.ISupplierPort;
import hust.project.restaurant_management.utils.GenerateCodeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class CreateSupplierUseCase {
    private final ISupplierPort supplierPort;
    private final ISupplierMapper supplierMapper;

    @Transactional
    public SupplierEntity createSupplier(CreateSupplierRequest request) {
        SupplierEntity supplier = supplierMapper.toEntityFromRequest(request);
        supplier.setTotalCost(0.0);

        if (!StringUtils.hasText(request.getCode())) {
            supplier.setCode(GenerateCodeUtils.generateCode("NCC", supplierPort.getMaxId() + 1));
        }

        return supplierPort.save(supplier);
    }
}
