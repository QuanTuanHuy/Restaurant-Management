package hust.project.restaurant_management.entity.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetMenuItemRequest {
    private Long page;
    private Long pageSize;
    private Long menuSectionId;
    private String title;
}
