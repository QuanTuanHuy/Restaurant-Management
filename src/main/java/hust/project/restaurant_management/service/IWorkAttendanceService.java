package hust.project.restaurant_management.service;

import hust.project.restaurant_management.entity.WorkAttendanceEntity;
import hust.project.restaurant_management.entity.dto.request.GetWorkAttendanceRequest;
import hust.project.restaurant_management.entity.dto.request.UpdateWorkAttendanceRequest;

import java.util.List;

public interface IWorkAttendanceService {
    List<WorkAttendanceEntity> getAllWorkAttendances(GetWorkAttendanceRequest filter);

    WorkAttendanceEntity getDetailWorkAttendance(Long id);

    WorkAttendanceEntity updateWorkAttendance(Long id, UpdateWorkAttendanceRequest request);
}
