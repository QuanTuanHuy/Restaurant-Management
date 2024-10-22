package hust.project.restaurant_management.repository.specification;

import hust.project.restaurant_management.entity.dto.request.GetTableRequest;
import hust.project.restaurant_management.model.TableModel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

public class TableSpecification {
    public static Specification<TableModel> getAll(GetTableRequest filter) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();

            if (StringUtils.hasText(filter.getLocation())) {
                predicates.add(builder.equal(root.get("location"), filter.getLocation()));
            }

            if (StringUtils.hasText(filter.getName())) {
                predicates.add(builder.like(root.get("name"), "%".concat(filter.getName()).concat("%")));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
