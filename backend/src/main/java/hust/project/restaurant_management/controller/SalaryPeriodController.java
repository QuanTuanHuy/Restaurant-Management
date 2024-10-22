package hust.project.restaurant_management.controller;

import hust.project.restaurant_management.entity.dto.request.CreateSalaryPeriodRequest;
import hust.project.restaurant_management.entity.dto.request.GetSalaryPeriodRequest;
import hust.project.restaurant_management.entity.dto.response.Resource;
import hust.project.restaurant_management.service.ISalaryPeriodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/salary_periods")
public class SalaryPeriodController {
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";

    private final ISalaryPeriodService salaryPeriodService;

    @PostMapping
    public ResponseEntity<Resource> createSalaryPeriod(@RequestBody CreateSalaryPeriodRequest request) {
        return ResponseEntity.ok(new Resource(salaryPeriodService.createSalaryPeriod(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getDetailSalaryPeriod(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(new Resource(salaryPeriodService.getDetailSalaryPeriod(id)));
    }

    @GetMapping("/{id}/async_calculate")
    public ResponseEntity<Resource> asyncCalculateSalaryPeriod(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(new Resource(salaryPeriodService.asyncCalculateSalaryPeriod(id)));
    }

    @GetMapping
    public ResponseEntity<Resource> getAll(
            @RequestParam(name = "page", defaultValue = DEFAULT_PAGE) Long page,
            @RequestParam(name = "page_size", defaultValue = DEFAULT_PAGE_SIZE) Long pageSize,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "status", required = false) String status
    ) {
        var filter = GetSalaryPeriodRequest.builder()
                .page(page).pageSize(pageSize)
                .title(title).status(status)
                .build();
        return ResponseEntity.ok(new Resource(salaryPeriodService.getAllSalaryPeriods(filter)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Resource> deleteSalaryPeriod(@PathVariable(name = "id") Long id) {
        salaryPeriodService.deleteSalaryPeriod(id);
        return ResponseEntity.ok(new Resource(null));
    }
}
