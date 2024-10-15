
package hust.project.restaurant_management.controller;

import hust.project.restaurant_management.entity.dto.request.*;
import hust.project.restaurant_management.entity.dto.response.Resource;
import hust.project.restaurant_management.service.IStockHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam(name = "page_size", defaultValue = DEFAULT_PAGE_SIZE) Long pageSize
    ) {
        var filter = GetStockHistoryRequest.builder()
                .page(page).pageSize(pageSize)
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

}
