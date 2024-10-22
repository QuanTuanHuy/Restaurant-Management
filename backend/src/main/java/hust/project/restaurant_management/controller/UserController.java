package hust.project.restaurant_management.controller;

import hust.project.restaurant_management.entity.dto.request.CreateUserRequest;
import hust.project.restaurant_management.entity.dto.request.GetUserRequest;
import hust.project.restaurant_management.entity.dto.request.UpdateUserRequest;
import hust.project.restaurant_management.entity.dto.response.Resource;
import hust.project.restaurant_management.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";

    private final IUserService userService;

    @PostMapping
    public ResponseEntity<Resource> createUser(@RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(new Resource(userService.createUser(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getDetailUser(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(new Resource(userService.getDetailUser(id)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Resource> getAllUsers(
            @RequestParam(name = "page", defaultValue = DEFAULT_PAGE) Long page,
            @RequestParam(name = "page_size", defaultValue = DEFAULT_PAGE_SIZE) Long pageSize,
            @RequestParam(name = "phone_number", required = false) String phoneNumber,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "role_id", required = false) Long roleId
    ) {
        var filter = GetUserRequest.builder()
                .page(page).pageSize(pageSize).phoneNumber(phoneNumber).name(name).roleId(roleId)
                .build();

        return ResponseEntity.ok(new Resource(userService.getAll(filter)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Resource> updateUser(
            @PathVariable(name = "id") Long id,
            @RequestBody UpdateUserRequest request
    ) {
        return ResponseEntity.ok(new Resource(userService.updateUser(id, request)));
    }
}
