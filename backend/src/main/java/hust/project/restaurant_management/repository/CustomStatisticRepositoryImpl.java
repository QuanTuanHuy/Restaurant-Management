package hust.project.restaurant_management.repository;

import hust.project.restaurant_management.entity.dto.request.GetStatisticByCustomerRequest;
import hust.project.restaurant_management.entity.dto.request.GetStatisticByRevenueRequest;
import hust.project.restaurant_management.entity.dto.response.CustomerStatisticPerDateResponse;
import hust.project.restaurant_management.entity.dto.response.CustomerStatisticPerHourResponse;
import hust.project.restaurant_management.entity.dto.response.RevenueStatisticPerDateResponse;
import hust.project.restaurant_management.entity.dto.response.RevenueStatisticPerHourResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomStatisticRepositoryImpl implements CustomStatisticRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<RevenueStatisticPerDateResponse> getStatisticByRevenue(GetStatisticByRevenueRequest request) {
        String sql =
                        "WITH RECURSIVE date_range AS (\n" +
                        "    SELECT DATE(:startDate) AS date\n" +
                        "    UNION ALL\n" +
                        "    SELECT date + INTERVAL 1 DAY\n" +
                        "    FROM date_range\n" +
                        "    WHERE date + INTERVAL 1 DAY <= DATE(:endDate)\n" +
                        ")\n" +
                        "SELECT date, SUM(IF(order_status = 'COMPLETED', total_cost, 0))\n" +
                        "FROM date_range LEFT JOIN orders ON DATE(check_in_time) = date\n" +
                        "GROUP BY date";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("startDate", request.getStartDate());
        query.setParameter("endDate", request.getEndDate());

        List<Object[]> result = query.getResultList();

        return result.stream()
                .map(r -> RevenueStatisticPerDateResponse.builder()
                    .date(((java.sql.Date) r[0]).toLocalDate())
                    .revenue((Double) r[1])
                    .build())
                .toList();
    }

    @Override
    public List<CustomerStatisticPerDateResponse> getStatisticByCustomer(GetStatisticByCustomerRequest request) {
        String sql =
                        "WITH RECURSIVE date_range AS (\n" +
                        "    SELECT DATE(:startDate) AS date\n" +
                        "    UNION ALL\n" +
                        "    SELECT date + INTERVAL 1 DAY\n" +
                        "    FROM date_range\n" +
                        "    WHERE date + INTERVAL 1 DAY <= DATE(:endDate)\n" +
                        ")\n" +
                        "SELECT date, SUM(IF(order_status = 'COMPLETED', number_of_people, 0))\n" +
                        "FROM date_range LEFT JOIN orders ON DATE(check_in_time) = date\n" +
                        "GROUP BY date";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("startDate", request.getStartDate());
        query.setParameter("endDate", request.getEndDate());

        List<Object[]> result = query.getResultList();


        return result.stream()
                .map(r -> CustomerStatisticPerDateResponse.builder()
                        .date(((java.sql.Date) r[0]).toLocalDate())
                        .count(Long.parseLong(r[1].toString()))
                        .build())
                .toList();
    }

    @Override
    public List<RevenueStatisticPerHourResponse> getStatisticByRevenueAndHour(GetStatisticByRevenueRequest request) {
        String sql =
                "WITH RECURSIVE hour_list AS\n" +
                "(\n" +
                "\tSELECT 0 AS hour\n" +
                "    UNION ALL\n" +
                "    SELECT hour + 1 FROM hour_list\n" +
                "    WHERE hour < 23\n" +
                ")\n" +
                "SELECT hour, SUM(IF(order_status = 'COMPLETED', total_cost, 0)) AS result\n" +
                "FROM hour_list LEFT JOIN \n" +
                "(\n" +
                "\tSELECT check_in_time, total_cost, order_status FROM orders\n" +
                "\tWHERE DATE(check_in_time) BETWEEN :startDate AND :endDate\n" +
                ") AS t ON hour(t.check_in_time) = hour\n" +
                "GROUP BY hour\n" +
                "ORDER BY 1 ASC";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("startDate", request.getStartDate());
        query.setParameter("endDate", request.getEndDate());

        List<Object[]> result = query.getResultList();

        return result.stream()
                .map(r -> RevenueStatisticPerHourResponse.builder()
                        .hour(Integer.parseInt(r[0].toString()))
                        .revenue((Double) r[1])
                        .build())
                .toList();
    }

    @Override
    public List<CustomerStatisticPerHourResponse> getStatisticByCustomerAndHour(GetStatisticByCustomerRequest request) {
        String sql =
                "WITH RECURSIVE hour_list AS\n" +
                "(\n" +
                "\tSELECT 0 AS hour\n" +
                "    UNION ALL\n" +
                "    SELECT hour + 1 FROM hour_list\n" +
                "    WHERE hour < 23\n" +
                ")\n" +
                "SELECT hour, SUM(IF(order_status = 'COMPLETED', number_of_people, 0)) AS result\n" +
                "FROM hour_list LEFT JOIN \n" +
                "(\n" +
                "\tSELECT check_in_time, number_of_people, order_status FROM orders\n" +
                "\tWHERE DATE(check_in_time) BETWEEN :startDate AND :endDate\n" +
                ") AS t ON hour(t.check_in_time) = hour\n" +
                "GROUP BY hour\n" +
                "ORDER BY 1 ASC";


        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("startDate", request.getStartDate());
        query.setParameter("endDate", request.getEndDate());

        List<Object[]> result = query.getResultList();

        return result.stream()
                .map(r -> CustomerStatisticPerHourResponse.builder()
                        .hour(Integer.parseInt(r[0].toString()))
                        .count(Integer.parseInt(r[1].toString()))
                        .build())
                .toList();
    }
}
