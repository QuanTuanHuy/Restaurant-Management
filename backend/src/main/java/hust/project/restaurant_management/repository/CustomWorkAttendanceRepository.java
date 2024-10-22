package hust.project.restaurant_management.repository;

import hust.project.restaurant_management.entity.dto.request.GetWorkAttendanceRequest;
import hust.project.restaurant_management.model.WorkAttendanceModel;

import java.util.List;

public interface CustomWorkAttendanceRepository {
    List<WorkAttendanceModel> getAllWorkAttendances(GetWorkAttendanceRequest filter);
}
