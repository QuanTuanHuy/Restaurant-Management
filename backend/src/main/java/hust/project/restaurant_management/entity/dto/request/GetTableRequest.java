package hust.project.restaurant_management.entity.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetTableRequest {
    private Long page;
    private Long pageSize;
    private String location;
    private Boolean isActive;
    private String name;
}
