package hust.project.restaurant_management.repository;

import hust.project.restaurant_management.entity.OrderEntity;
import hust.project.restaurant_management.entity.dto.request.GetOrderRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.model.OrderModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CustomOrderRepositoryImpl implements CustomOrderRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<OrderModel> getAllOrders(GetOrderRequest filter) {
        String sql = "SELECT * FROM orders WHERE id IN\n" +
                "(\n" +
                "\tSELECT DISTINCT o.id\n" +
                "    FROM orders o JOIN order_tables ot ON o.id = ot.order_id\n" +
                "    JOIN users u ON o.user_id = u.id\n" +
                "    JOIN customers c ON o.customer_id = c.id\n" +
                "    WHERE (o.check_in_time BETWEEN :startTime AND :endTime)\n";

        if (!CollectionUtils.isEmpty(filter.getOrderStatus())) {
            StringBuilder orderStatus = new StringBuilder();
            for (int i = 0; i < filter.getOrderStatus().size(); i++) {
                orderStatus.append("'" + filter.getOrderStatus().get(i) + "'");
                if (i < filter.getOrderStatus().size() - 1) {
                    orderStatus.append(", ");
                }
            }

            sql += "    AND o.order_status IN (" + orderStatus + ")\n";
        }
        if (StringUtils.hasText(filter.getPaymentMethod())) {
            sql += "    AND o.payment_method = '" + filter.getPaymentMethod() + "'\n";
        }
        if (StringUtils.hasText(filter.getUserName())) {
            sql += "    AND u.name LIKE '%" + filter.getUserName() + "%'\n";
        }
        if (StringUtils.hasText(filter.getCustomerName())) {
            sql += "    AND c.name LIKE '%" + filter.getCustomerName() + "%'\n";
        }
        if (StringUtils.hasText(filter.getNote())) {
            sql += "    AND o.note LIKE '%" + filter.getNote() + "%'\n";
        }
        if (filter.getTableIds() != null && !filter.getTableIds().isEmpty()) {
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
                ")";
        sql +=
                " LIMIT " + filter.getPageSize() + " OFFSET " + filter.getPage() * filter.getPageSize() + "\n";

        Query query = entityManager.createNativeQuery(sql, OrderModel.class);
        query.setParameter("startTime", filter.getStartTime());
        query.setParameter("endTime", filter.getEndTime());

        List<OrderModel> orders = query.getResultList();

        return orders;
    }
}
