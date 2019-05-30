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
     * 集合正常添加接口，无过期参数
     * @param id 目标集合的key
     * @param v value
     * @return 至少添加一行返回true，否则false
     */
    public boolean appendMemberSet(String id, String... v) {
        Long count = redisTemplate.opsForSet().add(id, v);
        return count > 0;
    }

    /**
     * 可自动回收token的添加接口
     * @param id key(非库中实际key)
     * @param v value
     * @return 至少添加一行返回true，否则false
     */
    public boolean appendTokenSetAuto(String id, String... v) {
        long suffix = LocalDate.now().toEpochDay();
        String key = id + suffix;
        long count = redisTemplate.opsForSet().add(key, v);
        redisTemplate.expire(key, TOKEN_DURATION_DAYS + EXTEND_DURATION_DAYS, TimeUnit.DAYS);
        return count > 0;
    }

    /**
     * 查询是否元素是否存在于集合的接口
     * @param id 目标集合的key
     * @param value 查询值
     * @return 存在返回true，否则false
     */
    public boolean isMemberInSet(String id, String value) {
        return redisTemplate.opsForSet().isMember(id, value);
    }

    /**
     * 可自动回收token的查询接口，效率低，待改进
     * @param id 目标集合的key
     * @param value 查询值
     * @return 存在返回true，否则false
     */
    public boolean isTokenInSetAuto(String id, String value) {
        long now = LocalDate.now().toEpochDay();
        long index = 0;
        while (index < TOKEN_DURATION_DAYS + 1) {
            if (redisTemplate.opsForSet().isMember(id + (now - index), value)) {
                return true;
            }
            index++;
        }
        return false;
    }

    public boolean deleteMemberSet(String id, String... v) {
        return redisTemplate.opsForSet().remove(id, v) > 0;
    }

    /**
     * 删除接口
     * @param id 目标集合的key
     * @param v 待删除值
     * @return 删除至少一条返回true，否则false
     */
    public boolean deleteTokenSetAuto(String id, String... v) {
        int count = 0;
        long now = LocalDate.now().toEpochDay();
        long index = 0;
        while (index < TOKEN_DURATION_DAYS + 1) {
            count += redisTemplate.opsForSet().remove(id + (now - index), v);
            index++;
        }
        return count != 0;
    }

    public boolean deleteSet(String id) {
        return redisTemplate.delete(id);
    }
}
