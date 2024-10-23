
package hust.project.restaurant_management.controller;

import hust.project.restaurant_management.entity.dto.request.GetSalaryDetailRequest;
import hust.project.restaurant_management.entity.dto.response.Resource;
import hust.project.restaurant_management.service.ISalaryDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/salary_details")
public class SalaryDetailController {
    private final ISalaryDetailService salaryDetailService;


    @GetMapping
    public ResponseEntity<Resource> getAllSalaryDetails(
            @RequestParam(name = "page", defaultValue = "0") Long page,
            @RequestParam(name = "page_size", defaultValue = "10") Long pageSize,
            @RequestParam(name = "user_id", required = false) Long userId,
            @RequestParam(name = "salary_period_id", required = false) Long salaryPeriodId,
            @RequestParam(name = "status", required = false) String status
    ) {
        var filter = GetSalaryDetailRequest.builder()
                .page(page).pageSize(pageSize)
                .userId(userId)
                .salaryPeriodId(salaryPeriodId)
                .status(status)
                .build();
        return ResponseEntity.ok(new Resource(salaryDetailService.getAllSalaryDetails(filter)));
    }

}
