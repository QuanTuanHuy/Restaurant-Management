package hust.project.restaurant_management.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "menu_setions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuSectionModel extends AuditTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;
}
