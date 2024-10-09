package hust.project.restaurant_management.repository;

import hust.project.restaurant_management.model.TableModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITableRepository extends IBaseRepository<TableModel>, CustomTableRepository {
    List<TableModel> findByIdIn(List<Long> ids);
}
