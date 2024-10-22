package hust.project.restaurant_management.repository.adapter;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.entity.ImageEntity;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.mapper.IImageMapper;
import hust.project.restaurant_management.model.ImageModel;
import hust.project.restaurant_management.port.IImagePort;
import hust.project.restaurant_management.repository.IImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageAdapter implements IImagePort {
    private final IImageRepository imageRepository;
    private final IImageMapper imageMapper;

    @Override
    public ImageEntity save(ImageEntity imageEntity) {
        try {
            ImageModel imageModel = imageMapper.toModelFromEntity(imageEntity);
            return imageMapper.toEntityFromModel(imageRepository.save(imageModel));
        } catch (Exception e) {
            log.error("[ImageAdapter] save image failed: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_IMAGE_FAILED);
        }
    }

    @Override
    public List<ImageEntity> saveAll(List<ImageEntity> imageEntities) {
        try {
            List<ImageModel> imageModels = imageMapper.toModelsFromEntities(imageEntities);
            return imageMapper.toEntitiesFromModels(imageRepository.saveAll(imageModels));
        } catch (Exception e) {
            log.error("[ImageAdapter] save all images failed: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_IMAGE_FAILED);
        }
    }

    @Override
    public List<ImageEntity> getImagesByEntityIdAndEntityType(Long entityId, String entityType) {
        return imageMapper.toEntitiesFromModels(imageRepository.findByEntityIdAndEntityType(entityId, entityType));
    }

    @Override
    public List<ImageEntity> getImagesByEntityIdInAndEntityType(List<Long> entityIds, String entityType) {
        return imageMapper.toEntitiesFromModels(
                imageRepository.findByEntityIdInAndEntityType(entityIds, entityType)
        );
    }

    @Override
    public void deleteImagesByIds(List<Long> ids) {
        try {
            imageRepository.deleteByIdIn(ids);
        } catch (Exception e) {
            log.error("[ImageAdapter] delete images failed: {}", e.getMessage());
            throw new AppException(ErrorCode.DELETE_IMAGE_FAILED);
        }
    }

    @Override
    public void deleteImagesByEntityIdAndEntityType(Long entityId, String entityType) {
        try {
            imageRepository.deleteByEntityIdAndEntityType(entityId, entityType);
        } catch (Exception e) {
            log.error("[ImageAdapter] delete images failed: {}", e.getMessage());
            throw new AppException(ErrorCode.DELETE_IMAGE_FAILED);
        }
    }
}
