package hust.project.restaurant_management.port;

import hust.project.restaurant_management.entity.ImageEntity;

import java.util.List;

public interface IImagePort {
    ImageEntity save(ImageEntity imageEntity);

    List<ImageEntity> saveAll(List<ImageEntity> imageEntities);

    List<ImageEntity> getImagesByEntityIdAndEntityType(Long entityId, String entityType);

    List<ImageEntity> getImagesByEntityIdInAndEntityType(List<Long> entityIds, String entityType);

    void deleteImagesByIds(List<Long> ids);

    void deleteImagesByEntityIdAndEntityType(Long entityId, String entityType);
}
