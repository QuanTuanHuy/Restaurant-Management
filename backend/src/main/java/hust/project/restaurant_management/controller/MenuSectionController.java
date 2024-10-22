package hust.project.restaurant_management.controller;

import hust.project.restaurant_management.entity.dto.request.*;
import hust.project.restaurant_management.entity.dto.response.Resource;
import hust.project.restaurant_management.service.IMenuSectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/menu_sections")
public class MenuSectionController {
    private final IMenuSectionService menuSectionService;

    @PostMapping
    public ResponseEntity<Resource> createMenuSection(@RequestBody CreateMenuSectionRequest request) {
        return ResponseEntity.ok(new Resource(menuSectionService.createMenuSection(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getDetailMenuSection(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(new Resource(menuSectionService.getDetailMenuSection(id)));
    }

    @GetMapping
    public ResponseEntity<Resource> getAll() {
        return ResponseEntity.ok(new Resource(menuSectionService.getAll()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Resource> updateMenuSection(
            @PathVariable(name = "id") Long id,
            @RequestBody UpdateMenuSectionRequest request
    ) {
        return ResponseEntity.ok(new Resource(menuSectionService.updateMenuSection(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Resource> deleteMenuSection(@PathVariable(name = "id") Long id) {
        menuSectionService.deleteMenuSection(id);
        return ResponseEntity.ok(new Resource(null));
    }
}
