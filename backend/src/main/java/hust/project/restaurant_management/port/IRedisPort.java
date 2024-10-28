package hust.project.restaurant_management.port;

import java.util.List;

public interface IRedisPort {
    void setCacheValueWithExpireTime(String key, Object value, Long expireTime);

    String getCacheString(String key);

    <T> T getCacheObject(String key, Class<T> clazz);

    <T> List<T> getCacheListObject(String key, Class<T> clazz);

    void deleteCache(String key);
}
