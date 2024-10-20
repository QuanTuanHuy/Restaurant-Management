package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.entity.ActivityLogEntity;
import hust.project.restaurant_management.entity.UserEntity;
import hust.project.restaurant_management.entity.dto.request.GetActivityLogRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.port.IActivityLogPort;
import hust.project.restaurant_management.port.IUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetActivityLogUseCase {
    private final IActivityLogPort activityLogPort;
    private final IUserPort userPort;

    public Pair<PageInfo, List<ActivityLogEntity>> getAllActivityLogs(GetActivityLogRequest filter) {
        var result = activityLogPort.getAllActivityLogs(filter);

        var activityLogs = result.getSecond();

        List<Long> userIds = activityLogs.stream()
                .map(ActivityLogEntity::getUserId)
                .toList();

        var users = userPort.getUsersByIds(userIds);

        var mapIdToUser = users.stream()
                .collect(Collectors.toMap(UserEntity::getId, Function.identity()));

        activityLogs.forEach(activityLog -> activityLog.setUserName(mapIdToUser.get(activityLog.getUserId()).getName()));

        return Pair.of(result.getFirst(), activityLogs);
    }
}
