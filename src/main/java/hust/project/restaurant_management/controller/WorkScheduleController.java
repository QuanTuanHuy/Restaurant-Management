
package hust.project.restaurant_management.controller;

import hust.project.restaurant_management.entity.dto.request.CreateWorkScheduleRequest;
import hust.project.restaurant_management.entity.dto.request.GetWorkScheduleRequest;
import hust.project.restaurant_management.entity.dto.response.Resource;
import hust.project.restaurant_management.service.IWorkScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/work_schedules")
public class WorkScheduleController {

    private final IWorkScheduleService workScheduleService;

    @PostMapping
    public ResponseEntity<Resource> createWorkSchedule(@RequestBody CreateWorkScheduleRequest request) {
        return ResponseEntity.ok(new Resource(workScheduleService.createWorkSchedule(request)));
    }

    @GetMapping
    public ResponseEntity<Resource> getAllWorkSchedules(
            @RequestParam(name = "user_ids", required = false) List<Long> userIds,
            @RequestParam(name = "start_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(name = "end_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate
    ) {
        var filter = GetWorkScheduleRequest.builder()
                .userIds(userIds)
                .startDate(startDate)
                .endDate(endDate)
                .build();
        return ResponseEntity.ok(new Resource(workScheduleService.getAllWorkSchedules(filter)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Resource> deleteWorkSchedule(
            @PathVariable(name = "id") Long id
    ) {
        workScheduleService.deleteWorkSchedule(id);
        return ResponseEntity.ok(new Resource(null));
    }

}
