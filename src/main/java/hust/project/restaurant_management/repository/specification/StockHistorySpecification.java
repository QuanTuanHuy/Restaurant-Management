package hust.project.restaurant_management.repository.specification;

import hust.project.restaurant_management.entity.dto.request.GetStockHistoryRequest;
import hust.project.restaurant_management.model.StockHistoryModel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class StockHistorySpecification {
    public static Specification<StockHistoryModel> getAllStockHistories(GetStockHistoryRequest filter) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();

            if (filter.getSupplierId() != null) {
                predicates.add(builder.equal(root.get("supplierId"), filter.getSupplierId()));
            }

            if (filter.getFromDate() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("dateTime"), filter.getFromDate()));
            }

            if (filter.getToDate() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("dateTime"), filter.getToDate()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
