package hust.project.restaurant_management.repository.specification;

import hust.project.restaurant_management.entity.dto.request.GetActivityLogRequest;
import hust.project.restaurant_management.model.ActivityLogModel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;

public class ActivityLogSpecification {
    public static Specification<ActivityLogModel> getAllActivityLogs(GetActivityLogRequest filter) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();

            Instant fromDate = filter.getFromDate().atZone(ZoneId.systemDefault()).toInstant();
            Instant toDate = filter.getToDate().atZone(ZoneId.systemDefault()).toInstant();

            predicates.add(builder.greaterThanOrEqualTo(root.get("createdAt"), fromDate));
            predicates.add(builder.lessThanOrEqualTo(root.get("createdAt"), toDate));

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
