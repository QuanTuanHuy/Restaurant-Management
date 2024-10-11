package hust.project.restaurant_management.repository;

import hust.project.restaurant_management.entity.OrderEntity;
import hust.project.restaurant_management.entity.dto.request.GetOrderRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CustomOrderRepositoryImpl implements CustomOrderRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Pair<PageInfo, List<OrderEntity>> getAllOrders(GetOrderRequest filter) {
        String sql = "SELECT * FROM orders WHERE id IN\n" +
                "(\n" +
                "\tSELECT o.id\n" +
                "    FROM orders o JOIN order_tables ot ON o.id = ot.order_id\n" +
                "    WHERE (o.check_in_time BETWEEN :startTime AND :endTime)\n";

        if (StringUtils.hasText(filter.getOrderStatus())) {
            sql += "    AND o.status = '" + filter.getOrderStatus() + "'\n";
        }
        if (StringUtils.hasText(filter.getPaymentMethod())) {
            sql += "    AND o.payment_method = '" + filter.getPaymentMethod() + "'\n";
        }
        if (filter.getUserId() != null) {
            sql += "    AND o.user_id = '" + filter.getUserId() + "'\n";
        }
        if (filter.getCustomerId() != null) {
            sql += "    AND o.customer_id = '" + filter.getCustomerId() + "'\n";
        }
        if (!filter.getTableIds().isEmpty()) {
            StringBuilder tableIds = new StringBuilder();
            for (int i = 0; i < filter.getTableIds().size(); i++) {
                tableIds.append(filter.getTableIds().get(i));
                if (i < filter.getTableIds().size() - 1) {
                    tableIds.append(", ");
                }
            }

            sql += "    AND ot.table_id IN (" + tableIds  + ")\n";
        }

        sql += "    ORDER BY o.check_in_time DESC\n" +
                "    LIMIT " + filter.getPageSize() + " OFFSET " + filter.getPage() * filter.getPageSize() + "\n" +
                ")";

        Query query = entityManager.createNativeQuery(sql, OrderEntity.class);
        List<OrderEntity> orders = query.getResultList();

        PageInfo pageInfo = PageInfo.builder()
                .pageSize(filter.getPageSize())
                .build();

        return Pair.of(pageInfo, orders);
    }
}
