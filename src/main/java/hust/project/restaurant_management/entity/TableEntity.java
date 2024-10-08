package hust.project.restaurant_management.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TableEntity {
    private Long id;

    private String name;

    private Long capacity;

    private String type;

    private String location;

    private Boolean isActive;
}
