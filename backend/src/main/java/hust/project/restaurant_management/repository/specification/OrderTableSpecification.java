package hust.project.restaurant_management.repository.specification;

import hust.project.restaurant_management.entity.dto.request.GetOrderTableRequest;
import hust.project.restaurant_management.model.OrderTableModel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class OrderTableSpecification {
    public static Specification<OrderTableModel> getAllOrderTables(GetOrderTableRequest filter) {
        return (root, query, builder) -> {
          var predicates = new ArrayList<Predicate>();

          if (filter.getOrderId() != null) {
                predicates.add(builder.equal(root.get("orderId"), filter.getOrderId()));
          }

          if (filter.getTableId() != null) {
              predicates.add(builder.equal(root.get("tableId"), filter.getTableId()));
          }

          return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
