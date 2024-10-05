package hust.project.restaurant_management.entity.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateMenuSectionRequest {
    private String title;
    private String description;
}
