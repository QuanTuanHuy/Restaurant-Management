package hust.project.restaurant_management.usecase;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.entity.TableEntity;
import hust.project.restaurant_management.entity.dto.request.GetTableAvailableRequest;
import hust.project.restaurant_management.entity.dto.request.GetTableRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.port.ITablePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetTableUseCase {
    private final ITablePort tablePort;

    public Pair<PageInfo, List<TableEntity>> getAllTables(GetTableRequest filter) {
        return tablePort.getAllTables(filter);
    }

    public TableEntity getDetailTable(Long id) {
        return tablePort.getTableById(id);
    }

    public List<TableEntity> getAllTablesAvailable(GetTableAvailableRequest filter) {
        if (filter.getCheckInTime().isAfter(filter.getCheckOutTime()) ||
                filter.getCheckInTime().isBefore(LocalDateTime.now())) {
            log.error("[GetTableUseCase] invalid time");
            throw new AppException(ErrorCode.TIME_INVALID);
        }
        return tablePort.getAllTablesAvailable(filter);
    }
}
