package hust.project.restaurant_management.entity.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateMenuSectionRequest {
    private String title;
    private String description;
}
