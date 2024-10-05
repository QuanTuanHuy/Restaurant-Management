package hust.project.restaurant_management.repository.specification;

import hust.project.restaurant_management.entity.dto.request.GetMenuItemRequest;
import hust.project.restaurant_management.model.MenuItemModel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class MenuItemSpecification {
    public static Specification<MenuItemModel> getAll(GetMenuItemRequest filter) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();

            if (filter.getMenuSectionId() != null) {
                predicates.add(builder.equal(root.get("menuSectionId"), filter.getMenuSectionId()));
            }

            if (filter.getTitle() != null) {
                predicates.add(builder.like(root.get("title"), "%".concat(filter.getTitle()).concat("%")));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
