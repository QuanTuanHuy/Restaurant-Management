
package hust.project.restaurant_management.controller;

import hust.project.restaurant_management.entity.dto.request.*;
import hust.project.restaurant_management.entity.dto.response.Resource;
import hust.project.restaurant_management.service.IOrderItemKitchenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/order_item_kitchens")
public class OrderItemKitchenController {

    private final IOrderItemKitchenService orderItemKitchenService;

    @GetMapping
    public ResponseEntity<Resource> getAll(
            @RequestParam(name = "status") String status,
            @RequestParam(name = "order_id", required = false) Long orderId,
            @RequestParam(name = "table_id", required = false) Long tableId
    ) {
        var filter = GetOrderItemKitchenRequest.builder()
                .status(status)
                .orderId(orderId)
                .tableId(tableId)
                .build();
        return ResponseEntity.ok(new Resource(orderItemKitchenService.getAllOrderItemKitchens(filter)));
    }

    @PutMapping
    public ResponseEntity<Resource> updateOrderItemKitchen(
            @RequestBody UpdateOrderItemKitchenStatusRequest request
    ) {
        orderItemKitchenService.updateOrderItemKitchenStatus(request);
        return ResponseEntity.ok(new Resource(null));
    }
}
