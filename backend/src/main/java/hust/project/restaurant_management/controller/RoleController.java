package hust.project.restaurant_management.controller;

import hust.project.restaurant_management.entity.dto.request.CreateRoleRequest;
import hust.project.restaurant_management.entity.dto.response.Resource;
import hust.project.restaurant_management.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/roles")
public class RoleController {
    private final IRoleService roleService;

    @PostMapping
    public ResponseEntity<Resource> createRole(@RequestBody CreateRoleRequest request) {
        return ResponseEntity.ok(new Resource(roleService.createRole(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getDetailRole(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(new Resource(roleService.getDetailRole(id)));
    }

    @GetMapping
    public ResponseEntity<Resource> getAllRoles() {
        return ResponseEntity.ok(new Resource(roleService.getAllRoles()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Resource> deleteRole(@PathVariable(name = "id") Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.ok(new Resource(null));
    }
}
