package hust.project.restaurant_management.repository.specification;

import hust.project.restaurant_management.entity.dto.request.GetUserRequest;
import hust.project.restaurant_management.model.UserModel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

public class UserSpecification {
    public static Specification<UserModel> getAll(GetUserRequest filter) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();

            if (filter.getPhoneNumber() != null) {
                predicates.add(builder.equal(root.get("phoneNumber"), filter.getPhoneNumber()));
            }

            if (StringUtils.hasText(filter.getName())) {
                predicates.add(builder.like(root.get("name"), "%".concat(filter.getName()).concat("%")));
            }

            if (filter.getRoleId() != null) {
                predicates.add(builder.equal(root.get("roleId"), filter.getRoleId()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
