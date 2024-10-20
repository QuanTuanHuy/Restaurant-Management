package hust.project.restaurant_management.controller;

import hust.project.restaurant_management.entity.dto.request.*;
import hust.project.restaurant_management.entity.dto.response.Resource;
import hust.project.restaurant_management.service.IOrderItemKitchenService;
import hust.project.restaurant_management.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_PAGE_SIZE = "30";

    private final IOrderService orderService;
    private final IOrderItemKitchenService orderItemKitchenService;

    @PostMapping
    public ResponseEntity<Resource> createOrder(@RequestBody CreateOrderRequest request) {
        return ResponseEntity.ok(new Resource(orderService.createOrder(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getDetailOrder(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(new Resource(orderService.getDetailOrder(id)));
    }

    @GetMapping
    public ResponseEntity<Resource> getAllOrders(
            @RequestParam(name = "page", defaultValue = DEFAULT_PAGE) Long page,
            @RequestParam(name = "page_size", defaultValue = DEFAULT_PAGE_SIZE) Long pageSize,
            @RequestParam(name = "order_status", required = false) String orderStatus,
            @RequestParam(name = "start_time") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @RequestParam(name = "end_time") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime,
            @RequestParam(name = "payment_method", required = false) String paymentMethod,
            @RequestParam(name = "user_id", required = false) Long userId,
            @RequestParam(name = "customer_id", required = false) Long customerId,
            @RequestParam(name = "table_ids", required = false) List<Long> tableIds
    ) {
        var filter = GetOrderRequest.builder()
                .page(page).pageSize(pageSize)
                .orderStatus(orderStatus).startTime(startTime).endTime(endTime)
                .paymentMethod(paymentMethod).userId(userId).customerId(customerId)
                .tableIds(tableIds)
                .build();
        return ResponseEntity.ok(new Resource(orderService.getAllOrders(filter)));
    }

    @PutMapping("/{id}/add_menu_items")
    public ResponseEntity<Resource> addMenuItemsToOrder(
            @PathVariable(name = "id") Long id,
            @RequestBody AddMenuItemsToOrderRequest request
    ) {
        orderService.addMenuItemsToOrder(id, request);
        orderItemKitchenService.createOrderItemKitchens(id, request);
        return ResponseEntity.ok(new Resource(null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resource> updateOrder(
            @PathVariable(name = "id") Long id,
            @RequestBody UpdateOrderRequest request
    ) {
        return ResponseEntity.ok(new Resource(orderService.updateOrder(id, request)));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Resource> updateOrderStatus(
            @PathVariable(name = "id") Long id,
            @RequestBody UpdateOrderStatusRequest request
    ) {
        orderService.updateOrderStatus(id, request);
        return ResponseEntity.ok(new Resource(null));
    }
}
