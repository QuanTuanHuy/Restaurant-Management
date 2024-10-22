
package hust.project.restaurant_management.controller;

import hust.project.restaurant_management.entity.dto.request.QuantityStockRequest;
import hust.project.restaurant_management.entity.dto.response.Resource;
import hust.project.restaurant_management.service.IStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/stocks")
public class StockController {
    private final IStockService stockService;


    @PatchMapping("/subtract")
    public ResponseEntity<Resource> subtractStockQuantity(
            @RequestBody QuantityStockRequest request
    ) {
        stockService.subtractQuantityStock(request);
        return ResponseEntity.ok(new Resource(null));
    }

}
