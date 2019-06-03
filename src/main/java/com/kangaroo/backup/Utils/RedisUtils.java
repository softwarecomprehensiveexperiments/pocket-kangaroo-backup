package com.kangaroo.backup.Utils;

import com.kangaroo.backup.Constant.TokenConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

/**
 * 封装的Redis缓存管理类（主要处理Set数据）
 *
 * 为了达到自动管理缓存的目的，内部封装了将一整个集合划分为多个小集合的功能
 * 每个小集合过期时间为设定的过期时间加一天，也就是服务器会多存放一天的过期信息
 * 可根据需要优化过期时间（颗粒度更小，相应库的条数会变多）
 */
@Component
public class RedisUtils {

    /**
     * token 默认保质期（30天）
     */
    private static final long TOKEN_DURATION_DAYS = TokenConstant.EXP_TIME / (1000 * 60 * 60 * 24);
    /**
     * 额外储存的时间（1天）
     */
    private static final long EXTEND_DURATION_DAYS = 1;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    /**
     * 基础接口：集合添加元素接口
     * @param key 目标集合的key
     * @param v value
     * @return 至少添加一行返回true，否则false
     */
    public boolean appendMemberSet(String key, String... v) {
        Long count = redisTemplate.opsForSet().add(key, v);
        return count > 0;
    }

    /**
     * 可自动回收token的集合添加接口
     * @param key key(非库中实际key)
     * @param v value
     * @return 至少添加一行返回true，否则false
     */
    public boolean appendTokenSetAuto(String key, String... v) {
        long suffix = LocalDate.now().toEpochDay();
        String realKey = key + suffix;
        long count = redisTemplate.opsForSet().add(realKey, v);
        redisTemplate.expire(realKey, TOKEN_DURATION_DAYS + EXTEND_DURATION_DAYS, TimeUnit.DAYS);
        return count > 0;
    }

    /**
     * 基础接口：添加string值键对的接口
     * @param key key
     * @param v value
     */
    public void appendValue(String key, String v) {
        redisTemplate.opsForValue().set(key, v);
    }

    /**
     * 基础接口：得到string值键对的值
     * @param key key
     * @return 对应值
     */
    public String getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 专门处理id的接口，可以返回并自增（从1开始）
     * @param key key
     * @return id值
     */
    public long getAndIncId(String key) {
        return redisTemplate.opsForValue().increment(key);
    }

    /**
     * 基础接口：查询是否元素是否存在于集合的接口
     * @param key 目标集合的key
     * @param value 查询值
     * @return 存在返回true，否则false
     */
    public boolean isMemberInSet(String key, String value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * 可自动回收token的查询接口，效率低，待改进
     * @param key 目标集合的key
     * @param value 查询值
     * @return 存在返回true，否则false
     */
    public boolean isTokenInSetAuto(String key, String value) {
        long now = LocalDate.now().toEpochDay();
        long index = 0;
        while (index < TOKEN_DURATION_DAYS + 1) {
            if (redisTemplate.opsForSet().isMember(key + (now - index), value)) {
                return true;
            }
            index++;
        }
        return false;
    }

    /**
     * 基础接口：删除集合中元素的接口
     * @param key key
     * @param v value
     * @return 至少删除一行返回true，否则返回false
     */
    public boolean deleteMemberSet(String key, String... v) {
        return redisTemplate.opsForSet().remove(key, v) > 0;
    }

    /**
     * 自动回收对应的删除接口
     * @param key 目标集合的key
     * @param v 待删除值
     * @return 删除至少一条返回true，否则false
     */
    public boolean deleteTokenSetAuto(String key, String... v) {
        int count = 0;
        long now = LocalDate.now().toEpochDay();
        long index = 0;
        while (index < TOKEN_DURATION_DAYS + 1) {
            count += redisTemplate.opsForSet().remove(key + (now - index), v);
            index++;
        }
        return count != 0;
    }

    /**
     * 基础接口：删除某个值键对（包括string，set等）
     * @param key key
     * @return 是否删除成功
     */
    public boolean delete(String key) {
        return redisTemplate.delete(key);
    }
}
