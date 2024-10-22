
package hust.project.restaurant_management.controller;

import hust.project.restaurant_management.entity.dto.request.CreateShiftRequest;
import hust.project.restaurant_management.entity.dto.request.UpdateShiftRequest;
import hust.project.restaurant_management.entity.dto.response.Resource;
import hust.project.restaurant_management.service.IShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/shifts")
public class ShiftController {

    private final IShiftService shiftService;

    @PostMapping
    public ResponseEntity<Resource> createShift(@RequestBody CreateShiftRequest request) {
        return ResponseEntity.ok(new Resource(shiftService.createShift(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getDetailShift(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(new Resource(shiftService.getDetailShift(id)));
    }

    @GetMapping
    public ResponseEntity<Resource> getAll() {
        return ResponseEntity.ok(new Resource(shiftService.getAllShifts()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resource> updateSupplier(
            @PathVariable(name = "id") Long id,
            @RequestBody UpdateShiftRequest request
    ) {
        return ResponseEntity.ok(new Resource(shiftService.updateShift(id, request)));
    }

}
