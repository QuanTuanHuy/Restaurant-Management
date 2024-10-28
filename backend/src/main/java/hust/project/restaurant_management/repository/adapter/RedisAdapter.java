package hust.project.restaurant_management.repository.adapter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.exception.AppException;
import hust.project.restaurant_management.port.IRedisPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisAdapter implements IRedisPort {
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void setCacheValueWithExpireTime(String key, Object value, Long expireTime) {
        try {
            redisTemplate.opsForValue().set(key, objectMapper.writeValueAsString(value), expireTime, TimeUnit.SECONDS);
        } catch (JsonProcessingException e) {
            throw new AppException(ErrorCode.JSON_PARSE_FAILED);
        }
    }

    @Override
    public String getCacheString(String key) {
       return (String) redisTemplate.opsForValue().get(key);
    }

    @Override
    public <T> T getCacheObject(String key, Class<T> clazz) {
        Object cacheData = redisTemplate.opsForValue().get(key);
        if (cacheData == null) {
            return null;
        }
        return objectMapper.convertValue(cacheData, clazz);
    }

    @Override
    public <T> List<T> getCacheListObject(String key, Class<T> clazz) {

        Object cacheData = redisTemplate.opsForValue().get(key);
        if (cacheData == null) {
            return null;
        }

        return objectMapper.convertValue(cacheData,
                objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }

    @Override
    public void deleteCache(String key) {
        redisTemplate.delete(key);
    }
}
