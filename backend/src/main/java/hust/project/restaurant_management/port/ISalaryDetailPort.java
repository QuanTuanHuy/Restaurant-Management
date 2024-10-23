package hust.project.restaurant_management.port;

import hust.project.restaurant_management.entity.SalaryDetailEntity;
import hust.project.restaurant_management.entity.dto.request.GetSalaryDetailRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface ISalaryDetailPort {
    SalaryDetailEntity save(SalaryDetailEntity salaryDetailEntity);

    List<SalaryDetailEntity> saveAll(List<SalaryDetailEntity> salaryDetailEntities);

    Pair<PageInfo, List<SalaryDetailEntity>> getAllSalaryDetails(GetSalaryDetailRequest filter);

    List<SalaryDetailEntity> getSalaryDetailsBySalaryPeriodId(Long salaryPeriodId);

    List<SalaryDetailEntity> getSalaryDetailsBySalaryPeriodIds(List<Long> salaryPeriodIds);

    List<SalaryDetailEntity> getSalaryDetailsByIds(List<Long> salaryDetailIds);

    List<SalaryDetailEntity> getSalaryDetailsByIdsAndSalaryPeriodId(List<Long> salaryDetailIds, Long salaryPeriodId);

    void deleteSalaryDetailsBySalaryPeriodId(Long salaryPeriodId);

    Long getMaxId();
}
