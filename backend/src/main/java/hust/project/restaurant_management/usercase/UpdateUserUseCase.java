package hust.project.restaurant_management.usercase;

import hust.project.restaurant_management.constants.GenderTypeEnum;
import hust.project.restaurant_management.constants.SalaryTypeEnum;
import hust.project.restaurant_management.entity.UserEntity;
import hust.project.restaurant_management.entity.dto.request.UpdateUserRequest;
import hust.project.restaurant_management.port.IRolePort;
import hust.project.restaurant_management.port.IUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateUserUseCase {
    private final IUserPort userPort;
    private final IRolePort rolePort;

    @Transactional
    public UserEntity updateUser(Long userId, UpdateUserRequest request) {
        UserEntity user = userPort.getUserById(userId);
        rolePort.getRoleById(request.getRoleId());

        user.setName(request.getName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setGender(GenderTypeEnum.valueOf(request.getGender()).name());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setRoleId(request.getRoleId());
        user.setCccd(request.getCccd());
        user.setCvImg(request.getCvImg());
        user.setPosition(request.getPosition());
        user.setSalaryType(SalaryTypeEnum.valueOf(request.getSalaryType()).name());
        user.setSalaryPerHour(request.getSalaryPerHour());
        user.setSalaryPerMonth(request.getSalaryPerMonth());


        return userPort.save(user);
    }
}
