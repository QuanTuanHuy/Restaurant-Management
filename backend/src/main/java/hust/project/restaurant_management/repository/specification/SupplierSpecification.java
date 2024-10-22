package hust.project.restaurant_management.repository.specification;

import hust.project.restaurant_management.entity.dto.request.GetSupplierRequest;
import hust.project.restaurant_management.model.SupplierModel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

public class SupplierSpecification {
    public static Specification<SupplierModel> getAllSuppliers(GetSupplierRequest filter) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();

            if (StringUtils.hasText(filter.getName())) {
                predicates.add(builder.like(root.get("name"), "%" + filter.getName() + "%"));
            }

            if (StringUtils.hasText(filter.getPhoneNumber())) {
                predicates.add(builder.like(root.get("phoneNumber"), "%" + filter.getPhoneNumber() + "%"));
            }

            if (filter.getDebtFrom() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("totalDebt"), filter.getDebtFrom()));
            }

            if (filter.getDebtTo() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("totalDebt"), filter.getDebtTo()));
            }

            if (filter.getTotalCostFrom() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("totalCost"), filter.getTotalCostFrom()));
            }

            if (filter.getTotalCostTo() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("totalCost"), filter.getTotalCostTo()));
            }

            if (StringUtils.hasText(filter.getStatus())) {
                predicates.add(builder.equal(root.get("status"), filter.getStatus()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
