package hust.project.restaurant_management.repository.specification;

import hust.project.restaurant_management.constants.SalaryDetailStatusEnum;
import hust.project.restaurant_management.entity.dto.request.GetSalaryDetailRequest;
import hust.project.restaurant_management.model.SalaryDetailModel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

public class SalaryDetailSpecification {
    public static Specification<SalaryDetailModel> getAllSalaryDetails(GetSalaryDetailRequest filter) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();

            if (filter.getSalaryPeriodId() != null) {
                predicates.add(builder.equal(root.get("salaryPeriodId"), filter.getSalaryPeriodId()));
            }

            if (filter.getUserId() != null) {
                predicates.add(builder.equal(root.get("userId"), filter.getUserId()));
            }

            if (StringUtils.hasText(filter.getStatus())) {
                predicates.add(builder.equal(root.get("status"), SalaryDetailStatusEnum.valueOf(filter.getStatus()).name()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
