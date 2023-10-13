package com.frame.redis.util;

import com.frame.redis.config.MultiRedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Admin
 */
@Component
public class RedisUtil {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private MultiRedisConnectionFactory connectionFactory;

    /**
     * key separator
     */
    public static final String CACHE_KEY_SEPARATOR = ".";

    /**
     * key colon
     */
    public static final String CACHE_KEY_COLON = ":";

    /**
     * default datasource
     */
    public static final String DEFAULT = "default";

    /**
     * frame sys id
     */
    public static final String frame_PREFIX = "frame";

    /**
     * OMD sys id
     */
    public static final String QMO_PREFIX = "qmo";

    /**
     * 构建缓存key
     *
     * @param strObjs
     * @return
     */
    public String buildKeyBySeparator(String... strObjs) {
        return String.join(CACHE_KEY_SEPARATOR, strObjs);
    }

    /**
     * 构建缓存key
     *
     * @param strObjs
     * @return
     */
    public String buildKeyByColon(String... strObjs) {
        return String.join(CACHE_KEY_COLON, strObjs);
    }

    /**
     * 模式匹配
     *
     * @param keyPattern
     * @return
     */
    public Set<String> getKeys(String datasource, int dbIndex, String keyPattern) {
        connectionFactory.setCurrentRedis(datasource, dbIndex);
        return redisTemplate.keys(keyPattern);
    }

    /**
     * 模式匹配
     *
     * @param keyPattern
     * @return
     */
    public Set<String> getKeys(int dbIndex, String keyPattern) {
        connectionFactory.setCurrentRedis(DEFAULT, dbIndex);
        return redisTemplate.keys(keyPattern);
    }


    /**
     * 是否存在key
     *
     * @param key
     * @return
     */
    public boolean exists(String datasource, int dbIndex, String key) {
        connectionFactory.setCurrentRedis(datasource, dbIndex);
        return redisTemplate.hasKey(key);
    }

    /**
     * 是否存在key
     *
     * @param key
     * @return
     */
    public boolean exists(int dbIndex, String key) {
        connectionFactory.setCurrentRedis(DEFAULT, dbIndex);
        return redisTemplate.hasKey(key);
    }


    /**
     * 删除key
     *
     * @param key
     * @return
     */
    public boolean delete(String datasource, int dbIndex, String key) {
        connectionFactory.setCurrentRedis(datasource, dbIndex);
        return redisTemplate.delete(key);
    }

    /**
     * 删除key
     *
     * @param key
     * @return
     */
    public boolean delete(int dbIndex, String key) {
        connectionFactory.setCurrentRedis(DEFAULT, dbIndex);
        return redisTemplate.delete(key);
    }


    /**
     * 获取value
     *
     * @param key
     * @return
     */
    public String get(String datasource, int dbIndex, String key) {
        connectionFactory.setCurrentRedis(datasource, dbIndex);
        return (String) redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取value
     *
     * @param key
     * @return
     */
    public String get(int dbIndex, String key) {
        connectionFactory.setCurrentRedis(DEFAULT, dbIndex);
        return (String) redisTemplate.opsForValue().get(key);
    }

    /**
     * 新增
     *
     * @param key
     * @param value
     */
    public void set(String datasource, int dbIndex, String key, String value) {
        connectionFactory.setCurrentRedis(datasource, dbIndex);
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 新增
     *
     * @param key
     * @param value
     */
    public void set(int dbIndex, String key, String value) {
        connectionFactory.setCurrentRedis(DEFAULT, dbIndex);
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 新增附加超时时间
     *
     * @param key
     * @param value
     * @param time
     * @param timeUnit
     * @return
     */
    public boolean setNx(String datasource, int dbIndex, String key, String value, Long time, TimeUnit timeUnit) {
        connectionFactory.setCurrentRedis(datasource, dbIndex);
        return redisTemplate.opsForValue().setIfAbsent(key, value, time, timeUnit);
    }

    /**
     * 新增附加超时时间
     *
     * @param key
     * @param value
     * @param time
     * @param timeUnit
     * @return
     */
    public boolean setNx(int dbIndex, String key, String value, Long time, TimeUnit timeUnit) {
        connectionFactory.setCurrentRedis(DEFAULT, dbIndex);
        return redisTemplate.opsForValue().setIfAbsent(key, value, time, timeUnit);
    }

    /**
     * 获取过期时间
     *
     * @param key
     * @return
     */
    public long getExpire(String datasource, int dbIndex, String key) {
        connectionFactory.setCurrentRedis(datasource, dbIndex);
        return redisTemplate.opsForValue().getOperations().getExpire(key);
    }

    /**
     * 获取过期时间
     *
     * @param key
     * @return
     */
    public long getExpire(int dbIndex, String key) {
        connectionFactory.setCurrentRedis(DEFAULT, dbIndex);
        return redisTemplate.opsForValue().getOperations().getExpire(key);
    }

    /**
     * 重置超时时间
     *
     * @param key
     * @param time
     * @param timeUnit
     */
    public void expire(String datasource, int dbIndex, String key, Long time, TimeUnit timeUnit) {
        connectionFactory.setCurrentRedis(datasource, dbIndex);
        redisTemplate.expire(key, time, timeUnit);
    }

    /**
     * 重置超时时间
     *
     * @param key
     * @param time
     * @param timeUnit
     */
    public void expire(int dbIndex, String key, Long time, TimeUnit timeUnit) {
        connectionFactory.setCurrentRedis(DEFAULT, dbIndex);
        redisTemplate.expire(key, time, timeUnit);
    }


    /**
     * 清空redis
     *
     * @param datasource
     * @param dbIndex
     * @param keys
     */
    public void clear(String datasource, int dbIndex, Set keys) {
        connectionFactory.setCurrentRedis(datasource, dbIndex);
        redisTemplate.delete(keys);
    }

    /**
     * 清空redis
     *
     * @param dbIndex
     * @param keys
     */
    public void clear(int dbIndex, Set keys) {
        connectionFactory.setCurrentRedis(DEFAULT, dbIndex);
        redisTemplate.delete(keys);
    }
}
