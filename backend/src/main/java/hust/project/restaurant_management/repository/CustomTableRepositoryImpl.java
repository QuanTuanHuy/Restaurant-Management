package hust.project.restaurant_management.repository;

import hust.project.restaurant_management.entity.dto.request.GetTableAvailableRequest;
import hust.project.restaurant_management.model.TableModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomTableRepositoryImpl implements CustomTableRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TableModel> getAllTablesAvailable(GetTableAvailableRequest filter) {
        String sql = "SELECT * FROM tables\n" +
                "WHERE is_active = TRUE AND id NOT IN\n" +
                "(\n" +
                "\tSELECT DISTINCT(ot.table_id)\n" +
                "    FROM order_tables ot JOIN orders o ON ot.order_id = o.id\n" +
                "    WHERE o.check_in_time BETWEEN :checkInTime AND :checkOutTime\n" +
                "    OR o.check_out_time BETWEEN :checkInTime AND :checkOutTime\n" +
                ")";

        Query query = entityManager.createNativeQuery(sql, TableModel.class);
        query.setParameter("checkInTime", filter.getCheckInTime());
        query.setParameter("checkOutTime", filter.getCheckOutTime());

        return query.getResultList();
    }
}
