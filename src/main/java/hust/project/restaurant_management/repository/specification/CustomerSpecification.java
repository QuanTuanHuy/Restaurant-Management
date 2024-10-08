package hust.project.restaurant_management.repository.specification;

import hust.project.restaurant_management.entity.dto.request.GetCustomerRequest;
import hust.project.restaurant_management.model.CustomerModel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

public class CustomerSpecification {
    public static Specification<CustomerModel> getAllCustomers(GetCustomerRequest filter) {
        return ((root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();

            if (StringUtils.hasText(filter.getName())) {
                predicates.add(builder.like(root.get("name"), "%".concat(filter.getName()).concat("%")));
            }

            if (StringUtils.hasText(filter.getPhoneNumber())) {
                predicates.add(builder.like(root.get("phoneNumber"), filter.getPhoneNumber().concat("%")));
            }

            if (StringUtils.hasText(filter.getAddress())) {
                predicates.add(builder.like(root.get("address"), "%".concat(filter.getAddress()).concat("%")));
            }

            if (StringUtils.hasText(filter.getGender())) {
                predicates.add(builder.equal(root.get("gender"), filter.getGender()));
            }

            if (filter.getBeginTotalCost() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("totalCost"), filter.getBeginTotalCost()));
            }

            if (filter.getEndTotalCost() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("totalCost"), filter.getEndTotalCost()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
