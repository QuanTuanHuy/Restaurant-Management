package hust.project.restaurant_management.repository;

import hust.project.restaurant_management.model.SalaryDetailModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISalaryDetailRepository extends IBaseRepository<SalaryDetailModel> {
    List<SalaryDetailModel> findBySalaryPeriodId(Long salaryPeriodId);

    List<SalaryDetailModel> findBySalaryPeriodIdIn(List<Long> salaryPeriodIds);

    void deleteBySalaryPeriodId(Long salaryPeriodId);

    @Query("SELECT MAX(s.id) FROM SalaryDetailModel s")
    Long getMaxId();
}
