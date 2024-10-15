package hust.project.restaurant_management.repository;

import hust.project.restaurant_management.model.ProductModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends IBaseRepository<ProductModel> {
    List<ProductModel> findByIdIn(List<Long> ids);

    @Query("SELECT MAX(p.id) FROM ProductModel p")
    Long getMaxId();
}
