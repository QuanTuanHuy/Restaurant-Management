package hust.project.restaurant_management.repository.adapter;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.entity.SupplierEntity;
import hust.project.restaurant_management.entity.dto.request.GetSupplierRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.mapper.ISupplierMapper;
import hust.project.restaurant_management.model.SupplierModel;
import hust.project.restaurant_management.port.ISupplierPort;
import hust.project.restaurant_management.repository.ISupplierRepository;
import hust.project.restaurant_management.repository.specification.SupplierSpecification;
import hust.project.restaurant_management.utils.PageInfoUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SupplierAdapter implements ISupplierPort {
    private final ISupplierRepository supplierRepository;
    private final ISupplierMapper supplierMapper;

    @Override
    public SupplierEntity save(SupplierEntity entity) {
        try {
            SupplierModel supplierModel = supplierMapper.toModelFromEntity(entity);
            return supplierMapper.toEntityFromModel(supplierRepository.save(supplierModel));
        } catch (Exception e) {
            log.error("[SupplierAdapter] save supplier failed, err: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_SUPPLIER_FAILED);
        }
    }

    @Override
    public Pair<PageInfo, List<SupplierEntity>> getAllSuppliers(GetSupplierRequest filter) {
        Pageable pageable = PageRequest.of(Math.toIntExact(filter.getPage()), Math.toIntExact(filter.getPageSize()),
                Sort.by("name").ascending());

        var result = supplierRepository.findAll(SupplierSpecification.getAllSuppliers(filter), pageable);

        var pageInfo = PageInfoUtils.getPageInfo(result);

        return Pair.of(pageInfo, supplierMapper.toEntitiesFromModels(result.getContent()));
    }

    @Override
    public SupplierEntity getSupplierById(Long id) {
        return supplierMapper.toEntityFromModel(supplierRepository.findById(id).orElseThrow(() -> {
            log.error("[SupplierAdapter] get detail supplier failed, supplier not found");
            return new AppException(ErrorCode.CREATE_SUPPLIER_FAILED);
        }));
    }

    @Override
    public Long getMaxId() {
        var result = supplierRepository.getMaxId();
        return result == null ? 0L : result;
    }
}
