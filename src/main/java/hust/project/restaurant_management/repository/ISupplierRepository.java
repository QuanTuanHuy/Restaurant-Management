package hust.project.restaurant_management.repository;

import hust.project.restaurant_management.model.SupplierModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISupplierRepository extends IBaseRepository<SupplierModel> {
    @Query("SELECT MAX(s.id) FROM SupplierModel s")
    Long getMaxId();

    List<SupplierModel> findByIdIn(List<Long> ids);
}
