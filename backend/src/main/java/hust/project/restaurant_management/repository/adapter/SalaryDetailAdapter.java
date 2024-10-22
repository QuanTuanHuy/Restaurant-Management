package hust.project.restaurant_management.repository.adapter;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.entity.SalaryDetailEntity;
import hust.project.restaurant_management.entity.dto.request.GetSalaryDetailRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.mapper.ISalaryDetailMapper;
import hust.project.restaurant_management.model.SalaryDetailModel;
import hust.project.restaurant_management.port.ISalaryDetailPort;
import hust.project.restaurant_management.repository.ISalaryDetailRepository;
import hust.project.restaurant_management.repository.specification.SalaryDetailSpecification;
import hust.project.restaurant_management.utils.PageInfoUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SalaryDetailAdapter implements ISalaryDetailPort {
    private final ISalaryDetailRepository salaryDetailRepository;

    private final ISalaryDetailMapper salaryDetailMapper;

    @Override
    public SalaryDetailEntity save(SalaryDetailEntity salaryDetailEntity) {
        try {
            SalaryDetailModel salaryDetailModel = salaryDetailMapper.toModelFromEntity(salaryDetailEntity);
            return salaryDetailMapper.toEntityFromModel(salaryDetailRepository.save(salaryDetailModel));
        } catch (Exception e) {
            log.error("[SalaryDetailAdapter] save salary detail failed, error: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_SALARY_DETAIL_FAILED);
        }
    }

    @Override
    public List<SalaryDetailEntity> saveAll(List<SalaryDetailEntity> salaryDetailEntities) {
        try {
            List<SalaryDetailModel> salaryDetailModels = salaryDetailMapper.toModelsFromEntities(salaryDetailEntities);
            return salaryDetailMapper.toEntitiesFromModels(salaryDetailRepository.saveAll(salaryDetailModels));
        } catch (Exception e) {
            log.error("[SalaryDetailAdapter] save all salary details failed, error: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_SALARY_DETAIL_FAILED);
        }
    }

    @Override
    public Pair<PageInfo, List<SalaryDetailEntity>> getAllSalaryDetails(GetSalaryDetailRequest filter) {
        Pageable pageable = PageRequest.of(Math.toIntExact(filter.getPage()), Math.toIntExact(filter.getPageSize()),
                Sort.by("id").descending());

        var result = salaryDetailRepository.findAll(SalaryDetailSpecification.getAllSalaryDetails(filter), pageable);

        var pageInfo = PageInfoUtils.getPageInfo(result);

        return Pair.of(pageInfo, salaryDetailMapper.toEntitiesFromModels(result.getContent()));
    }

    @Override
    public List<SalaryDetailEntity> getSalaryDetailsBySalaryPeriodId(Long salaryPeriodId) {
        return salaryDetailMapper.toEntitiesFromModels(salaryDetailRepository.findBySalaryPeriodId(salaryPeriodId));
    }

    @Override
    public List<SalaryDetailEntity> getSalaryDetailsBySalaryPeriodIds(List<Long> salaryPeriodIds) {
        return salaryDetailMapper.toEntitiesFromModels(salaryDetailRepository.findBySalaryPeriodIdIn(salaryPeriodIds));
    }

    @Override
    public void deleteSalaryDetailsBySalaryPeriodId(Long salaryPeriodId) {
        try {
            salaryDetailRepository.deleteBySalaryPeriodId(salaryPeriodId);
        } catch (Exception e) {
            log.error("[SalaryDetailAdapter] delete salary detail by salary period id failed, error: {}", e.getMessage());
            throw new AppException(ErrorCode.DELETE_SALARY_DETAIL_FAILED);
        }
    }

    @Override
    public Long getMaxId() {
        var result = salaryDetailRepository.getMaxId();
        return result == null ? 0 : result;
    }
}
