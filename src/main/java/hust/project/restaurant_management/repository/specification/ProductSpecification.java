package hust.project.restaurant_management.repository.specification;

import hust.project.restaurant_management.entity.dto.request.GetProductRequest;
import hust.project.restaurant_management.model.ProductModel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

public class ProductSpecification {
    public static Specification<ProductModel> getAllProducts(GetProductRequest filter) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();

            if (StringUtils.hasText(filter.getName())) {
                predicates.add(builder.like(root.get("name"), "%" + filter.getName() + "%"));
            }

            if (StringUtils.hasText(filter.getProductType())) {
                predicates.add(builder.equal(root.get("productType"), filter.getProductType()));
            }

            if (StringUtils.hasText(filter.getStatus())) {
                predicates.add(builder.equal(root.get("status"), filter.getStatus()));
            }

            if (filter.getPriceFrom() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("sellingPrice"), filter.getPriceFrom()));
            }

            if (filter.getPriceTo() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("sellingPrice"), filter.getPriceTo()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
