package hust.project.restaurant_management.entity.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetUserRequest {
    private Long page;
    private Long pageSize;
    private String name;
    private String phoneNumber;
    private Long roleId;
}
