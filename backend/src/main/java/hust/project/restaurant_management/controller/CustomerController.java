package hust.project.restaurant_management.controller;

import hust.project.restaurant_management.entity.dto.request.*;
import hust.project.restaurant_management.entity.dto.response.Resource;
import hust.project.restaurant_management.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_PAGE_SIZE = "15";

    private final ICustomerService customerService;

    @PostMapping
    public ResponseEntity<Resource> createCustomer(@RequestBody CreateCustomerRequest request) {
        return ResponseEntity.ok(new Resource(customerService.createCustomer(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getDetailTable(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(new Resource(customerService.getCustomerDetail(id)));
    }

    @GetMapping
    public ResponseEntity<Resource> getAllCustomers(
            @RequestParam(name = "page", defaultValue = DEFAULT_PAGE) Long page,
            @RequestParam(name = "page_size", defaultValue = DEFAULT_PAGE_SIZE) Long pageSize,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "phone_number", required = false) String phoneNumber,
            @RequestParam(name = "address", required = false) String address,
            @RequestParam(name = "gender", required = false) String gender,
            @RequestParam(name = "begin_total_cost", required = false) Double beginTotalCost,
            @RequestParam(name = "end_total_cost", required = false) Double endTotalCost,
            @RequestParam(name = "begin_dob", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate beginDob,
            @RequestParam(name = "end_dob", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDob
    ) {
        var filter = GetCustomerRequest.builder()
                .page(page).pageSize(pageSize)
                .name(name)
                .phoneNumber(phoneNumber)
                .address(address)
                .gender(gender)
                .beginTotalCost(beginTotalCost)
                .endTotalCost(endTotalCost)
                .beginDateOfBirth(beginDob)
                .endDateOfBirth(endDob)
                .build();
        return ResponseEntity.ok(new Resource(customerService.getAllCustomers(filter)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resource> updateCustomer(
            @PathVariable(name = "id") Long id,
            @RequestBody UpdateCustomerRequest request
    ) {
        return ResponseEntity.ok(new Resource(customerService.updateCustomer(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Resource> deleteCustomer(@PathVariable(name = "id") Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok(new Resource(null));
    }
}
