package hust.project.restaurant_management.port;

import hust.project.restaurant_management.entity.ActivityLogEntity;
import hust.project.restaurant_management.entity.dto.request.GetActivityLogRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IActivityLogPort {
    ActivityLogEntity save(ActivityLogEntity activityLogEntity);

    Pair<PageInfo, List<ActivityLogEntity>> getAllActivityLogs(GetActivityLogRequest filter);
}
