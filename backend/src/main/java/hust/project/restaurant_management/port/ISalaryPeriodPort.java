package hust.project.restaurant_management.port;

import hust.project.restaurant_management.entity.SalaryPeriodEntity;
import hust.project.restaurant_management.entity.dto.request.GetSalaryPeriodRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.time.LocalDate;
import java.util.List;

public interface ISalaryPeriodPort {
    SalaryPeriodEntity save(SalaryPeriodEntity salaryPeriodEntity);

    Pair<PageInfo, List<SalaryPeriodEntity>> getAllSalaryPeriods(GetSalaryPeriodRequest filter);

    SalaryPeriodEntity getSalaryPeriodById(Long id);

    void deleteSalaryPeriod(Long id);

    Boolean isExistSalaryPeriodInTime(LocalDate fromDate, LocalDate toDate);

    Long getMaxId();
}
