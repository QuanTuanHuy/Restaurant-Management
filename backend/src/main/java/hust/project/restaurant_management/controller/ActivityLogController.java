
package hust.project.restaurant_management.controller;

import hust.project.restaurant_management.entity.dto.request.GetActivityLogRequest;
import hust.project.restaurant_management.entity.dto.response.Resource;
import hust.project.restaurant_management.service.IActivityLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/activity_logs")
public class ActivityLogController {
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_PAGE_SIZE = "15";

    private final IActivityLogService activityLogService;

    @GetMapping
    public ResponseEntity<Resource> getAllActivityLogs(
            @RequestParam(name = "page", defaultValue = DEFAULT_PAGE) Long page,
            @RequestParam(name = "page_size", defaultValue = DEFAULT_PAGE_SIZE) Long pageSize,
            @RequestParam(name = "from_date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime fromDate,
            @RequestParam(name = "to_date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime toDate
    ) {
        var filter = GetActivityLogRequest.builder()
                .page(page).pageSize(pageSize)
                .fromDate(fromDate).toDate(toDate)
                .build();
        return ResponseEntity.ok(new Resource(activityLogService.getAllActivityLogs(filter)));
    }

}
