package hust.project.restaurant_management.usecase;

import hust.project.restaurant_management.entity.SalaryDetailEntity;
import hust.project.restaurant_management.entity.UserEntity;
import hust.project.restaurant_management.entity.dto.request.GetSalaryDetailRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.port.ISalaryDetailPort;
import hust.project.restaurant_management.port.IUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetSalaryDetailUseCase {
    private final ISalaryDetailPort salaryDetailPort;
    private final IUserPort userPort;

    public Pair<PageInfo, List<SalaryDetailEntity>> getAllSalaryDetails(GetSalaryDetailRequest filter) {
        var result = salaryDetailPort.getAllSalaryDetails(filter);
        var salaryDetails = result.getSecond();

        List<Long> userIds;
        if (filter.getUserId() != null) {
            userIds = List.of(filter.getUserId());
        } else {
            userIds = salaryDetails.stream().map(SalaryDetailEntity::getUserId).distinct().toList();
        }

        var mapIdToUserName = userPort.getUsersByIds(userIds)
                .stream()
                .collect(Collectors.toMap(UserEntity::getId, UserEntity::getName));

        salaryDetails.forEach(salaryDetail -> salaryDetail.setUserName(mapIdToUserName.getOrDefault(salaryDetail.getUserId(), null)));

        return result;
    }
}
