
package hust.project.restaurant_management.controller;

import hust.project.restaurant_management.entity.dto.request.*;
import hust.project.restaurant_management.entity.dto.response.Resource;
import hust.project.restaurant_management.service.ISupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/suppliers")
public class SupplierController {
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_PAGE_SIZE = "15";

    private final ISupplierService supplierService;

    @PostMapping
    public ResponseEntity<Resource> createSupplier(@RequestBody CreateSupplierRequest request) {
        return ResponseEntity.ok(new Resource(supplierService.createSupplier(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getDetailSupplier(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(new Resource(supplierService.getDetailSupplier(id)));
    }

    @GetMapping
    public ResponseEntity<Resource> getAll(
            @RequestParam(name = "page", defaultValue = DEFAULT_PAGE) Long page,
            @RequestParam(name = "page_size", defaultValue = DEFAULT_PAGE_SIZE) Long pageSize,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "phone_number", required = false) String phoneNumber,
            @RequestParam(name = "debt_from", required = false) Double debtFrom,
            @RequestParam(name = "debt_to", required = false) Double debtTo,
            @RequestParam(name = "total_cost_from", required = false) Double totalCostFrom,
            @RequestParam(name = "total_cost_to", required = false) Double totalCostTo,
            @RequestParam(name = "status", required = false) String status
    ) {
        var filter = GetSupplierRequest.builder()
                .page(page).pageSize(pageSize)
                .name(name)
                .phoneNumber(phoneNumber)
                .debtFrom(debtFrom).debtTo(debtTo)
                .totalCostFrom(totalCostFrom).totalCostTo(totalCostTo)
                .status(status)
                .build();
        return ResponseEntity.ok(new Resource(supplierService.getAllSuppliers(filter)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Resource> updateSupplier(
            @PathVariable(name = "id") Long id,
            @RequestBody UpdateSupplierRequest request
    ) {
        return ResponseEntity.ok(new Resource(supplierService.updateSupplier(id, request)));
    }

}
