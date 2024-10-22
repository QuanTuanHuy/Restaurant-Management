package hust.project.restaurant_management.repository.adapter;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.entity.SalaryPeriodEntity;
import hust.project.restaurant_management.entity.dto.request.GetSalaryPeriodRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.mapper.ISalaryPeriodMapper;
import hust.project.restaurant_management.model.SalaryPeriodModel;
import hust.project.restaurant_management.port.ISalaryPeriodPort;
import hust.project.restaurant_management.repository.ISalaryPeriodRepository;
import hust.project.restaurant_management.repository.specification.SalaryPeriodSpecification;
import hust.project.restaurant_management.utils.PageInfoUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SalaryPeriodAdapter implements ISalaryPeriodPort {
    private final ISalaryPeriodRepository salaryPeriodRepository;
    private final ISalaryPeriodMapper salaryPeriodMapper;

    @Override
    public SalaryPeriodEntity save(SalaryPeriodEntity salaryPeriodEntity) {
        try {
            SalaryPeriodModel salaryPeriodModel = salaryPeriodMapper.toModelFromEntity(salaryPeriodEntity);
            return salaryPeriodMapper.toEntityFromModel(salaryPeriodRepository.save(salaryPeriodModel));
        } catch (Exception e) {
            log.error("[SalaryPeriodAdapter] save salary period failed, error: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_SALARY_PERIOD_FAILED);
        }
    }

    @Override
    public Pair<PageInfo, List<SalaryPeriodEntity>> getAllSalaryPeriods(GetSalaryPeriodRequest filter) {
        Pageable pageable = PageRequest.of(Math.toIntExact(filter.getPage()), Math.toIntExact(filter.getPageSize()),
                Sort.by("id").descending());

        var result = salaryPeriodRepository.findAll(SalaryPeriodSpecification.getAllSalaryDetails(filter), pageable);

        var pageInfo = PageInfoUtils.getPageInfo(result);

        return Pair.of(pageInfo, salaryPeriodMapper.toEntitiesFromModels(result.getContent()));
    }

    @Override
    public void deleteSalaryPeriod(Long id) {
        try {
            salaryPeriodRepository.deleteById(id);
        } catch (Exception e) {
            log.error("[SalaryPeriodAdapter] delete salary period failed, error: {}", e.getMessage());
            throw new AppException(ErrorCode.DELETE_SALARY_PERIOD_FAILED);
        }
    }

    @Override
    public Boolean isExistSalaryPeriodInTime(LocalDate fromDate, LocalDate toDate) {
        List<SalaryPeriodModel> salaryPeriods = salaryPeriodRepository.findAll(
                SalaryPeriodSpecification.isStartDateInTimeRange(fromDate, toDate)
                        .or(SalaryPeriodSpecification.isEndDateInTimeRange(fromDate, toDate)));

        return !salaryPeriods.isEmpty();
    }

    @Override
    public Long getMaxId() {
        var result = salaryPeriodRepository.getMaxId();
        return result == null ? 0 : result;
    }
}
