package hust.project.restaurant_management.service;

import hust.project.restaurant_management.entity.SalaryDetailEntity;
import hust.project.restaurant_management.entity.dto.request.GetSalaryDetailRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface ISalaryDetailService {
    Pair<PageInfo, List<SalaryDetailEntity>> getAllSalaryDetails(GetSalaryDetailRequest filter);
}
