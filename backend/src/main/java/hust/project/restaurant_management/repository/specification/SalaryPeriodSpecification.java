package hust.project.restaurant_management.repository.specification;

import hust.project.restaurant_management.constants.SalaryPeriodStatusEnum;
import hust.project.restaurant_management.entity.dto.request.GetSalaryPeriodRequest;
import hust.project.restaurant_management.model.SalaryPeriodModel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;

public class SalaryPeriodSpecification {
    public static Specification<SalaryPeriodModel> getAllSalaryDetails(GetSalaryPeriodRequest filter) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();

            if (StringUtils.hasText(filter.getTitle())) {
                predicates.add(builder.like(root.get("title"), "%".concat(filter.getTitle()).concat("%")));
            }

            if (StringUtils.hasText(filter.getStatus())) {
                predicates.add(builder.equal(root.get("status"), SalaryPeriodStatusEnum.valueOf(filter.getStatus())));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<SalaryPeriodModel> isStartDateInTimeRange(LocalDate fromDate, LocalDate toDate) {
        return (root, query, builder) -> builder.between(root.get("fromDate"), fromDate, toDate);
    }
    public static Specification<SalaryPeriodModel> isEndDateInTimeRange(LocalDate fromDate, LocalDate toDate) {
        return (root, query, builder) -> builder.between(root.get("toDate"), fromDate, toDate);
    }

}
