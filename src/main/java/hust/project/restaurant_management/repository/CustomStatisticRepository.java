package hust.project.restaurant_management.repository;

import hust.project.restaurant_management.entity.dto.request.GetStatisticByCustomerRequest;
import hust.project.restaurant_management.entity.dto.request.GetStatisticByRevenueRequest;
import hust.project.restaurant_management.entity.dto.response.CustomerStatisticPerDateResponse;
import hust.project.restaurant_management.entity.dto.response.RevenueStatisticPerDateResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomStatisticRepository {
    List<RevenueStatisticPerDateResponse> getStatisticByRevenue(GetStatisticByRevenueRequest request);

    List<CustomerStatisticPerDateResponse> getStatisticByCustomer(GetStatisticByCustomerRequest request);
}
