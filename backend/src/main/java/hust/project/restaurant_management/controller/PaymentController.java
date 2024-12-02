
package hust.project.restaurant_management.controller;

import hust.project.restaurant_management.entity.dto.request.CreatePaymentRequest;
import hust.project.restaurant_management.entity.dto.response.Resource;
import hust.project.restaurant_management.service.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/payments")
public class PaymentController {
    private final IPaymentService paymentService;

    @PostMapping
    public ResponseEntity<Resource> createPayment(@RequestBody CreatePaymentRequest request) {
        return ResponseEntity.ok(new Resource(paymentService.createPayment(request)));
    }

    @GetMapping
    public ResponseEntity<Resource> getPaymentsByIds(@RequestParam(name = "ids") List<Long> ids) {
        return ResponseEntity.ok(new Resource(paymentService.getPaymentsByIds(ids)));
    }
}
