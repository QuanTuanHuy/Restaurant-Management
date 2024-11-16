package hust.project.restaurant_management.service.impl;

import hust.project.restaurant_management.entity.SupplierEntity;
import hust.project.restaurant_management.entity.dto.request.CreateSupplierRequest;
import hust.project.restaurant_management.entity.dto.request.GetSupplierRequest;
import hust.project.restaurant_management.entity.dto.request.UpdateSupplierRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.service.ISupplierService;
import hust.project.restaurant_management.usecase.CreateSupplierUseCase;
import hust.project.restaurant_management.usecase.GetSupplierUseCase;
import hust.project.restaurant_management.usecase.UpdateSupplierUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService implements ISupplierService {
    private final CreateSupplierUseCase createSupplierUseCase;
    private final GetSupplierUseCase getSupplierUseCase;
    private final UpdateSupplierUseCase updateSupplierUseCase;

    @Override
    public SupplierEntity createSupplier(CreateSupplierRequest request) {
        return createSupplierUseCase.createSupplier(request);
    }

    @Override
    public Pair<PageInfo, List<SupplierEntity>> getAllSuppliers(GetSupplierRequest filter) {
        return getSupplierUseCase.getAllSuppliers(filter);
    }

    @Override
    public SupplierEntity getDetailSupplier(Long id) {
        return getSupplierUseCase.getDetailSupplier(id);
    }

    @Override
    public SupplierEntity updateSupplier(Long id, UpdateSupplierRequest request) {
        return updateSupplierUseCase.updateSupplier(id, request);
    }
}
