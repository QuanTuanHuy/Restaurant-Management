package hust.project.restaurant_management.usecase;

import hust.project.restaurant_management.entity.SalaryDetailEntity;
import hust.project.restaurant_management.entity.SalaryPeriodEntity;
import hust.project.restaurant_management.entity.UserEntity;
import hust.project.restaurant_management.entity.dto.request.GetSalaryPeriodRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.port.ISalaryDetailPort;
import hust.project.restaurant_management.port.ISalaryPeriodPort;
import hust.project.restaurant_management.port.IUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetSalaryPeriodUseCase {
    private final ISalaryPeriodPort salaryPeriodPort;
    private final ISalaryDetailPort salaryDetailPort;
    private final IUserPort userPort;

    public Pair<PageInfo, List<SalaryPeriodEntity>> getAllSalaryPeriods(GetSalaryPeriodRequest filter) {
        var result = salaryPeriodPort.getAllSalaryPeriods(filter);
        var salaryPeriods = result.getSecond();

        var salaryPeriodIds = salaryPeriods.stream().map(SalaryPeriodEntity::getId).toList();

        List<SalaryDetailEntity> salaryDetails = salaryDetailPort.getSalaryDetailsBySalaryPeriodIds(salaryPeriodIds);


        setUserNameForSalaryDetails(salaryDetails);


        salaryPeriods.forEach(salaryPeriod -> salaryPeriod.setSalaryDetails(salaryDetails.stream()
                .filter(salaryDetail -> salaryDetail.getSalaryPeriodId().equals(salaryPeriod.getId()))
                .toList()
        ));

        return result;
    }

    public SalaryPeriodEntity getSalaryPeriodDetail(Long id) {
        SalaryPeriodEntity salaryPeriod = salaryPeriodPort.getSalaryPeriodById(id);

        List<SalaryDetailEntity> salaryDetails = salaryDetailPort.getSalaryDetailsBySalaryPeriodId(id);

        setUserNameForSalaryDetails(salaryDetails);

        salaryPeriod.setSalaryDetails(salaryDetails);

        return salaryPeriod;
    }


    private void setUserNameForSalaryDetails(List<SalaryDetailEntity> salaryDetails) {
        List<Long> userIds = salaryDetails.stream().map(SalaryDetailEntity::getUserId).distinct().toList();
        List<UserEntity> users = userPort.getUsersByIds(userIds);
        var mapIdToUserName = users.stream().collect(Collectors.toMap(UserEntity::getId, UserEntity::getName));

        salaryDetails.forEach(salaryDetail ->
                salaryDetail.setUserName(mapIdToUserName.getOrDefault(salaryDetail.getUserId(), null)));
    }
}
