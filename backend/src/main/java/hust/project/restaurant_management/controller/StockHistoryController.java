
package hust.project.restaurant_management.controller;

import hust.project.restaurant_management.entity.dto.request.*;
import hust.project.restaurant_management.entity.dto.response.Resource;
import hust.project.restaurant_management.service.IStockHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/stock_histories")
public class StockHistoryController {
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_PAGE_SIZE = "15";

    private final IStockHistoryService stockHistoryService;

    @PostMapping
    public ResponseEntity<Resource> createStockHistory(@RequestBody CreateStockHistoryRequest request) {
        return ResponseEntity.ok(new Resource(stockHistoryService.createStockHistory(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getDetailStockHistory(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(new Resource(stockHistoryService.getDetailStockHistory(id)));
    }

    @GetMapping
    public ResponseEntity<Resource> getAll(
            @RequestParam(name = "page", defaultValue = DEFAULT_PAGE) Long page,
            @RequestParam(name = "page_size", defaultValue = DEFAULT_PAGE_SIZE) Long pageSize,
            @RequestParam(name = "code", defaultValue = "") String code,
            @RequestParam(name = "statuses", required = false) List<String> statuses,
            @RequestParam(name = "supplier_name", defaultValue = "") String supplierName,
            @RequestParam(name = "user_name", defaultValue = "") String userName,
            @RequestParam(name = "product_name", defaultValue = "") String productName,
            @RequestParam(name = "note", defaultValue = "") String note,
            @RequestParam(name = "from_date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime fromDate,
            @RequestParam(name = "to_date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime toDate
    ) {
        var filter = GetStockHistoryRequest.builder()
                .page(page).pageSize(pageSize)
                .code(code)
                .statuses(statuses)
                .fromDate(fromDate).toDate(toDate)
                .supplierName(supplierName).userName(userName)
                .productName(productName)
                .note(note)
                .build();
        return ResponseEntity.ok(new Resource(stockHistoryService.getAllStockHistories(filter)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resource> updateStockHistory(
            @PathVariable(name = "id") Long id,
            @RequestBody UpdateStockHistoryRequest request
    ) {
        return ResponseEntity.ok(new Resource(stockHistoryService.updateStockHistory(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Resource> deleteStockHistory(
            @PathVariable(name = "id") Long id
    ) {
        stockHistoryService.deleteStockHistory(id);
        return ResponseEntity.ok(new Resource(null));
    }


}
