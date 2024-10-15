package hust.project.restaurant_management.port;

import hust.project.restaurant_management.entity.SupplierEntity;
import hust.project.restaurant_management.entity.dto.request.GetSupplierRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface ISupplierPort {
    SupplierEntity save(SupplierEntity entity);

    Pair<PageInfo, List<SupplierEntity>> getAllSuppliers(GetSupplierRequest filter);

    SupplierEntity getSupplierById(Long id);

    List<SupplierEntity> getSuppliersByIds(List<Long> ids);

    Long getMaxId();
}
