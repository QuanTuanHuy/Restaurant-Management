package hust.project.restaurant_management.repository;

import hust.project.restaurant_management.model.ImageModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IImageRepository extends IBaseRepository<ImageModel> {
    List<ImageModel> findByEntityIdAndEntityType(Long entityId, String entityType);

    List<ImageModel> findByEntityIdInAndEntityType(List<Long> entityIds, String entityType);

    void deleteByIdIn(List<Long> ids);

    void deleteByEntityIdAndEntityType(Long entityId, String entityType);
}
