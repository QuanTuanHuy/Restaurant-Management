package hust.project.restaurant_management.service.impl;

import hust.project.restaurant_management.entity.ActivityLogEntity;
import hust.project.restaurant_management.entity.dto.request.GetActivityLogRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.service.IActivityLogService;
import hust.project.restaurant_management.usecase.GetActivityLogUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityLogService implements IActivityLogService {
    private final GetActivityLogUseCase getActivityLogUseCase;

    @Override
    public Pair<PageInfo, List<ActivityLogEntity>> getAllActivityLogs(GetActivityLogRequest filter) {
        return getActivityLogUseCase.getAllActivityLogs(filter);
    }
}
