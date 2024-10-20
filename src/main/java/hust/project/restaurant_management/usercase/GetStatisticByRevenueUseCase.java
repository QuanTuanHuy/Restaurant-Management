package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.entity.dto.request.GetStatisticByRevenueRequest;
import hust.project.restaurant_management.entity.dto.response.StatisticByRevenueResponse;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.repository.CustomStatisticRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetStatisticByRevenueUseCase {
    private final CustomStatisticRepository customStatisticRepository;

    public StatisticByRevenueResponse getStatisticByRevenue(GetStatisticByRevenueRequest request) {
        if (request.getEndTime().isBefore(request.getStartTime())) {
            log.error("[GetStatisticByRevenueUseCase] Time invalid");
            throw new AppException(ErrorCode.TIME_INVALID);
        }

        return StatisticByRevenueResponse.builder()
                .revenueStatistics(customStatisticRepository.getStatisticByRevenue(request))
                .build();
    }
}
