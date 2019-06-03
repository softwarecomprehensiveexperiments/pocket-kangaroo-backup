package com.kangaroo.backup.Constant;

public interface TokenConstant {

    /**
     * Token过期时间设置为一个月
     */
    long EXP_TIME = 2592000000L;

    /**
     * Header部分，表示算法和类型
     */

    String JWT_HEADER_STRING = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";

    /**
     * 服务器（即发布者）的名称
     */
    String SERVER_ISS = "Kangaroo Backup";

    /**
     * Redis缓存中索引的key
     */
    String REDIS_TOKEN_KEY = "ActiveTokenIdSet";

    /**
     * Redis缓存中的tokenID对应key
     */
    String REDIS_TOKEN_ID_KEY = "CurrentTokenId";
}
