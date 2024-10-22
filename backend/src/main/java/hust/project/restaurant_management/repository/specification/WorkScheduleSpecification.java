package hust.project.restaurant_management.repository.specification;

import hust.project.restaurant_management.entity.dto.request.GetWorkScheduleRequest;
import hust.project.restaurant_management.model.WorkScheduleModel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;

public class WorkScheduleSpecification {
    public static Specification<WorkScheduleModel> getAllWorkSchedules(GetWorkScheduleRequest filter) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();

            if (filter.getStartDate() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("date"), filter.getStartDate()));
            }

            if (filter.getEndDate() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("date"), filter.getEndDate()));
            }

            if (!CollectionUtils.isEmpty(filter.getUserIds())) {
                predicates.add(root.get("userId").in(filter.getUserIds()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
