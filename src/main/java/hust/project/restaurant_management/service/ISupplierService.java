package hust.project.restaurant_management.service;

import hust.project.restaurant_management.entity.SupplierEntity;
import hust.project.restaurant_management.entity.dto.request.CreateSupplierRequest;
import hust.project.restaurant_management.entity.dto.request.GetSupplierRequest;
import hust.project.restaurant_management.entity.dto.request.UpdateSupplierRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface ISupplierService {
    SupplierEntity createSupplier(CreateSupplierRequest request);

    Pair<PageInfo, List<SupplierEntity>> getAllSuppliers(GetSupplierRequest filter);

    SupplierEntity getDetailSupplier(Long id);

    SupplierEntity updateSupplier(Long id, UpdateSupplierRequest request);
}
