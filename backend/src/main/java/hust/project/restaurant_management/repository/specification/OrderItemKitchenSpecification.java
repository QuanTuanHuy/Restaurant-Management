package hust.project.restaurant_management.repository.specification;

import hust.project.restaurant_management.constants.OrderItemKitchenStatusEnum;
import hust.project.restaurant_management.entity.dto.request.GetOrderItemKitchenRequest;
import hust.project.restaurant_management.model.OrderItemKitchenModel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

public class OrderItemKitchenSpecification {
    public static Specification<OrderItemKitchenModel> getAll(GetOrderItemKitchenRequest filter) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();

            if (StringUtils.hasText(filter.getStatus())) {
                predicates.add(builder.equal(root.get("status"),
                        OrderItemKitchenStatusEnum.valueOf(filter.getStatus()).name()));
            }

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
