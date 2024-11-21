package hust.project.restaurant_management.usecase;

import hust.project.restaurant_management.entity.SupplierEntity;
import hust.project.restaurant_management.entity.dto.request.GetSupplierRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.port.ISupplierPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetSupplierUseCase {
    private final ISupplierPort supplierPort;

    public Pair<PageInfo, List<SupplierEntity>> getAllSuppliers(GetSupplierRequest filter) {
        return supplierPort.getAllSuppliers(filter);
    }

    public SupplierEntity getDetailSupplier(Long id) {
        return supplierPort.getSupplierById(id);
    }
}
