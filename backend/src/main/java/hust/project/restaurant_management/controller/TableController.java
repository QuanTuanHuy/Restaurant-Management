package hust.project.restaurant_management.controller;

import hust.project.restaurant_management.entity.dto.request.*;
import hust.project.restaurant_management.entity.dto.response.Resource;
import hust.project.restaurant_management.service.ITableService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tables")
public class TableController {
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_PAGE_SIZE = "30";

    private final ITableService tableService;

    @PostMapping
    public ResponseEntity<Resource> createTable(@RequestBody CreateTableRequest request) {
        return ResponseEntity.ok(new Resource(tableService.createTable(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getDetailTable(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(new Resource(tableService.getDetailTable(id)));
    }

    @GetMapping
    public ResponseEntity<Resource> getAllTables(
            @RequestParam(name = "page", defaultValue = DEFAULT_PAGE) Long page,
            @RequestParam(name = "page_size", defaultValue = DEFAULT_PAGE_SIZE) Long pageSize,
            @RequestParam(name = "location", required = false) String location,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "isActive", required = false) Boolean isActive
    ) {
        var filter = GetTableRequest.builder()
                .page(page).pageSize(pageSize)
                .location(location).name(name).isActive(isActive)
                .build();
        return ResponseEntity.ok(new Resource(tableService.getAllTables(filter)));
    }

    @GetMapping("/available")
    public ResponseEntity<Resource> getAllTablesAvailable(
            @RequestParam(name = "check_in_time") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime checkInTime,
            @RequestParam(name = "check_out_time") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime checkOutTime
    ) {
        var filter = GetTableAvailableRequest.builder()
                .checkInTime(checkInTime).checkOutTime(checkOutTime)
                .build();
        return ResponseEntity.ok(new Resource(tableService.getAllTablesAvailable(filter)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Resource> updateTable(
            @PathVariable(name = "id") Long id,
            @RequestBody UpdateTableRequest request
    ) {
        return ResponseEntity.ok(new Resource(tableService.updateTable(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Resource> deleteTable(@PathVariable(name = "id") Long id) {
        tableService.deleteTable(id);
        return ResponseEntity.ok(new Resource(null));
    }
}
