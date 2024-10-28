package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.port.IRolePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteRoleUseCase {
    private final IRolePort rolePort;

    @Transactional
    public void deleteRole(Long id) {
        rolePort.deleteRoleById(id);
    }
}
