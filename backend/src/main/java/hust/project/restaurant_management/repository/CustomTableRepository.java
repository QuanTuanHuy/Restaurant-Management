package hust.project.restaurant_management.repository;

import hust.project.restaurant_management.entity.dto.request.GetTableAvailableRequest;
import hust.project.restaurant_management.model.TableModel;

import java.util.List;

public interface CustomTableRepository {
    List<TableModel> getAllTablesAvailable(GetTableAvailableRequest filter);
}
