package hust.project.restaurant_management.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageEntity {
    private Long id;

    private String url;

    private Long entityId;

    private String entityType;
}
