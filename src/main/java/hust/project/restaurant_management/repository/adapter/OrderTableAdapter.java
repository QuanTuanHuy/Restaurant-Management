package hust.project.restaurant_management.repository.adapter;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.entity.OrderTableEntity;
import hust.project.restaurant_management.entity.dto.request.GetOrderTableRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.mapper.IOrderTableMapper;
import hust.project.restaurant_management.model.OrderTableModel;
import hust.project.restaurant_management.port.IOrderTablePort;
import hust.project.restaurant_management.repository.IOrderTableRepository;
import hust.project.restaurant_management.repository.specification.OrderTableSpecification;
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
public class OrderTableAdapter implements IOrderTablePort {
    private final IOrderTableRepository orderTableRepository;
    private final IOrderTableMapper orderTableMapper;

    @Override
    public OrderTableEntity save(OrderTableEntity orderTableEntity) {
        try {
            OrderTableModel orderTableModel = orderTableMapper.toModelFromEntity(orderTableEntity);
            return orderTableMapper.toEntityFromModel(orderTableRepository.save(orderTableModel));
        } catch (Exception e) {
            log.error("[OrderTableAdapter] save order table failed: {} ", e.getMessage());
            throw new AppException(ErrorCode.CREATE_ORDER_FAILED);
        }
    }

    @Override
    public List<OrderTableEntity> saveAll(List<OrderTableEntity> orderTableEntities) {
        try {
            List<OrderTableModel> models = orderTableMapper.toModelsFromEntities(orderTableEntities);
            return orderTableMapper.toEntitiesFromModels(orderTableRepository.saveAll(models));
        } catch (Exception e) {
            log.error("[OrderTableAdapter] save all order tables failed: {} ", e.getMessage());
            throw new AppException(ErrorCode.CREATE_ORDER_FAILED);
        }
    }


    @Override
    public List<OrderTableEntity> getOrderTablesByOrderId(Long orderId) {
        return orderTableMapper.toEntitiesFromModels(orderTableRepository.findByOrderId(orderId));
    }

    @Override
    public List<OrderTableEntity> getAllOrderTablesByTableId(Long tableId) {
        return orderTableMapper.toEntitiesFromModels(orderTableRepository.findByTableId(tableId));
    }

    @Override
    public Pair<PageInfo, List<OrderTableEntity>> getAllOrderTables(GetOrderTableRequest filter) {
        Pageable pageable = PageRequest.of(Math.toIntExact(filter.getPage()), Math.toIntExact(filter.getPageSize()),
                Sort.by("id").descending());

        var result = orderTableRepository.findAll(OrderTableSpecification.getAllOrderTables(filter), pageable);

        var pageInfo = PageInfoUtils.getPageInfo(result);

        return Pair.of(pageInfo, orderTableMapper.toEntitiesFromModels(result.getContent()));
    }

    @Override
    public void deleteOrderTableByIds(List<Long> ids) {
        try {
            orderTableRepository.deleteByIdIn(ids);
        } catch (Exception e) {
            log.error("[OrderTableAdapter] delete order table failed: {} ", e.getMessage());
            throw new AppException(ErrorCode.DELETE_ORDER_FAILED);
        }
    }
}
