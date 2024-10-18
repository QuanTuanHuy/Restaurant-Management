package hust.project.restaurant_management.repository;

import hust.project.restaurant_management.entity.dto.request.GetWorkAttendanceRequest;
import hust.project.restaurant_management.model.WorkAttendanceModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomWorkAttendanceRepositoryImpl implements CustomWorkAttendanceRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<WorkAttendanceModel> getAllWorkAttendances(GetWorkAttendanceRequest filter) {
        String sql = "SELECT * FROM work_attendances\n" +
                "WHERE id IN\n" +
                "(\n" +
                "\tSELECT wa.id\n" +
                "    FROM work_attendances wa JOIN work_schedules wc ON wa.work_schedule_id = wc.id\n" +
                "    WHERE wc.date BETWEEN :startDate AND :endDate\n";

        if (filter.getUserId() != null) {
            sql += "    AND wc.user_id = " + filter.getUserId() + "\n";
        }

        if (filter.getShiftId() != null) {
            sql += "    AND wc.shift_id = " + filter.getShiftId() + "\n";
        }

        sql += ")";

        Query query = entityManager.createNativeQuery(sql, WorkAttendanceModel.class);

        query.setParameter("startDate", filter.getStartDate());
        query.setParameter("endDate", filter.getEndDate());

        return query.getResultList();
    }
}
