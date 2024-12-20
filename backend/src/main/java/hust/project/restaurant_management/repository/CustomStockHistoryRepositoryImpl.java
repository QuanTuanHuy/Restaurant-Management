package hust.project.restaurant_management.repository;

import hust.project.restaurant_management.entity.dto.request.GetStockHistoryRequest;
import hust.project.restaurant_management.entity.dto.response.PageInfo;
import hust.project.restaurant_management.model.StockHistoryModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
public class CustomStockHistoryRepositoryImpl implements CustomStockHistoryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Pair<PageInfo, List<StockHistoryModel>> getAllStockHistories(GetStockHistoryRequest filter) {
        String sql = """
        WITH T AS (
            SELECT sh.id 
            FROM stock_histories sh
            JOIN users u ON sh.user_id = u.id
            JOIN suppliers s ON sh.supplier_id = s.id
            WHERE sh.date_time BETWEEN :fromDate AND :toDate
              AND sh.status IN (:statuses)
              AND sh.code LIKE :code
              AND sh.note LIKE :note
              AND u.name LIKE :userName
              AND s.name LIKE :supplierName
        ),
        V AS (
            SELECT DISTINCT t.id 
            FROM T t
            JOIN stock_history_items shi ON t.id = shi.stock_history_id
            JOIN products p ON shi.product_id = p.id
            WHERE p.name LIKE :productName
        )
        SELECT sh.*
        FROM stock_histories sh 
        JOIN V v ON sh.id = v.id
        ORDER BY sh.date_time DESC
        LIMIT :pageSize OFFSET :offset
        """;

        Query query = entityManager.createNativeQuery(sql, StockHistoryModel.class);


        query.setParameter("fromDate", filter.getFromDate());
        query.setParameter("toDate", filter.getToDate());
        query.setParameter("statuses", filter.getStatuses());
        query.setParameter("code", "%" + filter.getCode() + "%");
        query.setParameter("note", "%" + filter.getNote() + "%");
        query.setParameter("userName", "%" + filter.getUserName() + "%");
        query.setParameter("supplierName", "%" + filter.getSupplierName() + "%");
        query.setParameter("productName", "%" + filter.getProductName() + "%");
        query.setParameter("pageSize", filter.getPageSize());
        query.setParameter("offset", filter.getPage() * filter.getPageSize());


        List<StockHistoryModel> stockHistoryModels = query.getResultList();


        String sql2 = """
        WITH T AS (
            SELECT sh.id 
            FROM stock_histories sh
            JOIN users u ON sh.user_id = u.id
            JOIN suppliers s ON sh.supplier_id = s.id
            WHERE sh.date_time BETWEEN :fromDate AND :toDate
              AND sh.status IN (:statuses)
              AND sh.code LIKE :code
              AND sh.note LIKE :note
              AND u.name LIKE :userName
              AND s.name LIKE :supplierName
        ),
        V AS (
            SELECT DISTINCT t.id 
            FROM T t
            JOIN stock_history_items shi ON t.id = shi.stock_history_id
            JOIN products p ON shi.product_id = p.id
            WHERE p.name LIKE :productName
        )
        SELECT COUNT(DISTINCT sh.id)
        FROM stock_histories sh 
        JOIN V v ON sh.id = v.id
        """;

        Query secondQuery = entityManager.createNativeQuery(sql2);

        secondQuery.setParameter("fromDate", filter.getFromDate());
        secondQuery.setParameter("toDate", filter.getToDate());
        secondQuery.setParameter("statuses", filter.getStatuses());
        secondQuery.setParameter("code", "%" + filter.getCode() + "%");
        secondQuery.setParameter("note", "%" + filter.getNote() + "%");
        secondQuery.setParameter("userName", "%" + filter.getUserName() + "%");
        secondQuery.setParameter("supplierName", "%" + filter.getSupplierName() + "%");
        secondQuery.setParameter("productName", "%" + filter.getProductName() + "%");

        List<Object> secondResult = secondQuery.getResultList();
        Long totalRecord = Long.parseLong(secondResult.get(0).toString());

        PageInfo pageInfo = PageInfo.builder()
                .totalPage((long) Math.ceil((double) totalRecord / filter.getPageSize()))
                .pageSize((long) stockHistoryModels.size())
                .totalRecord(totalRecord)
                .previousPage(filter.getPage() > 0 ? filter.getPage() - 1 : null)
                .nextPage((filter.getPage() + 1) * filter.getPageSize() < totalRecord ? filter.getPage() + 1 : null)
                .build();

        return Pair.of(pageInfo, stockHistoryModels);
    }


    private StringBuilder buildStockHistoryStatusStr(List<String> statuses) {
        StringBuilder statusStr = new StringBuilder();
        for (int i = 0; i < statuses.size(); i++) {
            statusStr.append("'" + statuses.get(i) + "'");
            if (i < statuses.size() - 1) {
                statusStr.append(", ");
            }
        }
        return statusStr;
    }
}
