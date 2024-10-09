package hust.project.restaurant_management.repository.adapter;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.entity.TableEntity;
import hust.project.restaurant_management.entity.dto.request.GetTableAvailableRequest;
import hust.project.restaurant_management.entity.dto.request.GetTableRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.mapper.ITableMapper;
import hust.project.restaurant_management.model.TableModel;
import hust.project.restaurant_management.port.ITablePort;
import hust.project.restaurant_management.repository.ITableRepository;
import hust.project.restaurant_management.repository.specification.TableSpecification;
import hust.project.restaurant_management.utils.PageInfoUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TableAdapter implements ITablePort {
    private final ITableRepository tableRepository;
    private final ITableMapper tableMapper;

    @Override
    public TableEntity save(TableEntity tableEntity) {
        try {
            TableModel tableModel = tableMapper.toModelFromEntity(tableEntity);
            return tableMapper.toEntityFromModel(tableRepository.save(tableModel));
        } catch (Exception e) {
            log.error("[TableAdapter] create table failed, {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_TABLE_FAILED);
        }
    }

    @Override
    public Pair<PageInfo, List<TableEntity>> getAllTables(GetTableRequest filter) {
        Pageable pageable = PageRequest.of(Math.toIntExact(filter.getPage()), Math.toIntExact(filter.getPageSize()),
                Sort.by("name").ascending());

        var result = tableRepository.findAll(TableSpecification.getAll(filter), pageable);

        var pageInfo = PageInfoUtils.getPageInfo(result);

        return Pair.of(pageInfo, tableMapper.toEntitiesFromModels(result.getContent()));
    }

    @Override
    public List<TableEntity> getAllTables() {
        return tableMapper.toEntitiesFromModels(tableRepository.findAll());
    }

    @Override
    public List<TableEntity> getTablesByIds(List<Long> ids) {
        return tableMapper.toEntitiesFromModels(tableRepository.findByIdIn(ids));
    }

    @Override
    public List<TableEntity> getAllTablesAvailable(GetTableAvailableRequest filter) {
        return tableMapper.toEntitiesFromModels(tableRepository.getAllTablesAvailable(filter));
    }

    @Override
    public TableEntity getTableById(Long id) {
        return tableMapper.toEntityFromModel(tableRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TABLE_NOT_FOUND)));
    }

    @Override
    public void deleteTableById(Long id) {
        try {
            tableRepository.deleteById(id);
        } catch (Exception e) {
            log.error("[TableAdapter] delete table failed, {}", e.getMessage());
            throw new AppException(ErrorCode.DELETE_TABLE_FAILED);
        }
    }
}
