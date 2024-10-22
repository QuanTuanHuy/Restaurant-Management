package hust.project.restaurant_management.repository;

import hust.project.restaurant_management.model.SalaryPeriodModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ISalaryPeriodRepository extends IBaseRepository<SalaryPeriodModel> {
    @Query("SELECT MAX(s.id) FROM SalaryPeriodModel s")
    Long getMaxId();

}
