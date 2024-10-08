package hust.project.restaurant_management.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "menu_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItemModel extends AuditTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "cost_price")
    private Double costPrice;

    @Column(name = "selling_price")
    private Double sellingPrice;

    @Column(name = "thumbnail_img")
    private String thumbnailImg;

    @Column(name = "menu_section_id")
    private Long menuSectionId;
}
