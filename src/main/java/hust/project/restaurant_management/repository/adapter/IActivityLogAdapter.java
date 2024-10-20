package hust.project.restaurant_management.repository.adapter;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.entity.ActivityLogEntity;
import hust.project.restaurant_management.entity.dto.request.GetActivityLogRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.mapper.IActivityLogMapper;
import hust.project.restaurant_management.model.ActivityLogModel;
import hust.project.restaurant_management.port.IActivityLogPort;
import hust.project.restaurant_management.repository.IActivityLogRepository;
import hust.project.restaurant_management.repository.specification.ActivityLogSpecification;
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
public class IActivityLogAdapter implements IActivityLogPort {
    private final IActivityLogRepository activityLogRepository;
    private final IActivityLogMapper activityLogMapper;

    @Override
    public ActivityLogEntity save(ActivityLogEntity activityLogEntity) {
        try {
            ActivityLogModel activityLogModel = activityLogMapper.toModelFromEntity(activityLogEntity);
            return activityLogMapper.toEntityFromModel(activityLogRepository.save(activityLogModel));
        } catch (Exception e) {
            log.error("[ActivityLogAdapter] save activity log failed, error: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_ACTIVITY_LOG_FAILED);
        }
    }

    @Override
    public Pair<PageInfo, List<ActivityLogEntity>> getAllActivityLogs(GetActivityLogRequest filter) {
        Pageable pageable = PageRequest.of(Math.toIntExact(filter.getPage()), Math.toIntExact(filter.getPageSize()),
                Sort.by("createdAt").descending());

        var result = activityLogRepository.findAll(ActivityLogSpecification.getAllActivityLogs(filter), pageable);

        var pageInfo = PageInfoUtils.getPageInfo(result);

        return Pair.of(pageInfo, activityLogMapper.toEntitiesFromModels(result.getContent()));
    }
}
