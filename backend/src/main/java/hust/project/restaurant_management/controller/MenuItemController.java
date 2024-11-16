package hust.project.restaurant_management.controller;

import hust.project.restaurant_management.entity.dto.request.*;
import hust.project.restaurant_management.entity.dto.response.Resource;
import hust.project.restaurant_management.service.IMenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/menu_items")
public class MenuItemController {
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";

    private final IMenuItemService menuItemService;

    @PostMapping
    public ResponseEntity<Resource> createMenuItem(@RequestBody CreateMenuItemRequest request) {
        return ResponseEntity.ok(new Resource(menuItemService.createMenuItem(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getDetailMenuItem(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(new Resource(menuItemService.getDetailMenuItem(id)));
    }

    @GetMapping
    public ResponseEntity<Resource> getAll(
            @RequestParam(name = "page", defaultValue = DEFAULT_PAGE) Long page,
            @RequestParam(name = "page_size", defaultValue = DEFAULT_PAGE_SIZE) Long pageSize,
            @RequestParam(name = "menu_section_id", required = false) Long menuSectionId,
            @RequestParam(name = "title", required = false) String title
    ) {
        var filter = GetMenuItemRequest.builder()
                .page(page)
                .pageSize(pageSize)
                .menuSectionId(menuSectionId)
                .title(title)
                .build();
        return ResponseEntity.ok(new Resource(menuItemService.getAllMenuItems(filter)));
    }

    @GetMapping("/all")
    public ResponseEntity<Resource> getAll() {
        return ResponseEntity.ok(new Resource(menuItemService.getAllMenuItems()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resource> updateMenuItem(
            @PathVariable(name = "id") Long id,
            @RequestBody UpdateMenuItemRequest request
    ) {
        return ResponseEntity.ok(new Resource(menuItemService.updateMenuItem(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Resource> deleteMenuItem(@PathVariable(name = "id") Long id) {
        menuItemService.deleteMenuItem(id);
        return ResponseEntity.ok(new Resource(null));
    }
}
