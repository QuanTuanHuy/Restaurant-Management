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

    Long getMaxId();
}
