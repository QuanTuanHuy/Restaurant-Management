
package hust.project.restaurant_management.controller;

import hust.project.restaurant_management.entity.dto.request.GetWorkAttendanceRequest;
import hust.project.restaurant_management.entity.dto.request.UpdateWorkAttendanceRequest;
import hust.project.restaurant_management.entity.dto.response.Resource;
import hust.project.restaurant_management.service.IWorkAttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/work_attendances")
public class WorkAttendanceController {

    private final IWorkAttendanceService workAttendanceService;

    @GetMapping
    public ResponseEntity<Resource> getAllWorkAttendances(
            @RequestParam(name = "user_id", required = false) Long userId,
            @RequestParam(name = "shift_id", required = false) Long shiftId,
            @RequestParam(name = "start_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(name = "end_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate
    ) {
        var filter = GetWorkAttendanceRequest.builder()
                .userId(userId)
                .shiftId(shiftId)
                .startDate(startDate)
                .endDate(endDate)
                .build();
        return ResponseEntity.ok(new Resource(workAttendanceService.getAllWorkAttendances(filter)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getDetailWorkAttendance(
            @PathVariable(name = "id") Long id
    ) {
        return ResponseEntity.ok(new Resource(workAttendanceService.getDetailWorkAttendance(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resource> updateWorkAttendance(
            @PathVariable(name = "id") Long id,
            @RequestBody UpdateWorkAttendanceRequest request
            ) {
        return ResponseEntity.ok(new Resource(workAttendanceService.updateWorkAttendance(id, request)));
    }

}
