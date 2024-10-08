package hust.project.restaurant_management.entity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTableRequest {
    private String name;

    private Long capacity;

    private String type;

    private String location;

    private Boolean isActive;
}
