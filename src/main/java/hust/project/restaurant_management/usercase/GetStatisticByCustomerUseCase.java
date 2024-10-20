package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.entity.dto.request.GetStatisticByCustomerRequest;
import hust.project.restaurant_management.entity.dto.response.StatisticByCustomerResponse;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.repository.CustomStatisticRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetStatisticByCustomerUseCase {
    private final CustomStatisticRepository customStatisticRepositoryImpl;

    public StatisticByCustomerResponse getStatisticByCustomer(GetStatisticByCustomerRequest request) {
        if (request.getEndTime().isBefore(request.getStartTime())) {
            log.error("[GetStatisticByRevenueUseCase] Time invalid");
            throw new AppException(ErrorCode.TIME_INVALID);
        }

        return StatisticByCustomerResponse.builder()
                .customerStatistics(customStatisticRepositoryImpl.getStatisticByCustomer(request))
                .build();
    }
}
