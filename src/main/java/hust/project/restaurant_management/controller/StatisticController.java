package hust.project.restaurant_management.controller;

import hust.project.restaurant_management.entity.dto.request.GetStatisticByCustomerRequest;
import hust.project.restaurant_management.entity.dto.request.GetStatisticByMenuItemRequest;
import hust.project.restaurant_management.entity.dto.request.GetStatisticByRevenueRequest;
import hust.project.restaurant_management.entity.dto.response.Resource;
import hust.project.restaurant_management.service.IStatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/statistics")
public class StatisticController {
    private final IStatisticService statisticService;

    @GetMapping("/by_revenue")
    public ResponseEntity<Resource> getStatisticByRevenue(
            @RequestParam(name = "start_date") LocalDate startDate,
            @RequestParam(name = "end_date") LocalDate endDate
    ) {
        var request = GetStatisticByRevenueRequest.builder()
                .startDate(startDate).endDate(endDate)
                .build();
        return ResponseEntity.ok(new Resource(statisticService.getStatisticByRevenue(request)));
    }

    @GetMapping("/by_customer")
    public ResponseEntity<Resource> getStatisticByCustomer(
            @RequestParam(name = "start_date") LocalDate startDate,
            @RequestParam(name = "end_date") LocalDate endDate
    ) {
        var request = GetStatisticByCustomerRequest.builder()
                .startDate(startDate).endDate(endDate)
                .build();
        return ResponseEntity.ok(new Resource(statisticService.getStatisticByCustomer(request)));
    }

    @GetMapping("/by_menu_item")
    public ResponseEntity<Resource> getStatisticByMenuItem(
            @RequestParam(name = "start_time") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @RequestParam(name = "end_time") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime,
            @RequestParam(name = "category", defaultValue = "QUANTITY") String category,
            @RequestParam(name = "limit", defaultValue = "10") Integer limit
    ) {
        var request = GetStatisticByMenuItemRequest.builder()
                .startTime(startTime).endTime(endTime)
                .category(category)
                .limit(limit)
                .build();
        return ResponseEntity.ok(new Resource(statisticService.getStatisticByMenuItem(request)));
    }
}
