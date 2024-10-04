package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.port.IRolePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteRoleUseCase {
    private final IRolePort rolePort;

    public void deleteRole(Long id) {
        rolePort.deleteRoleById(id);
    }
}
