package hust.project.restaurant_management.repository;

import hust.project.restaurant_management.model.ShiftModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface IShiftRepository extends IBaseRepository<ShiftModel> {

    @Query("SELECT s FROM ShiftModel s WHERE (s.startTime BETWEEN :startTime AND :endTime) OR (s.endTime BETWEEN :startTime AND :endTime)")
    List<ShiftModel> findShiftOverlapping(@Param("startTime") LocalTime startTime,
                              @Param("endTime") LocalTime endTime);
}
