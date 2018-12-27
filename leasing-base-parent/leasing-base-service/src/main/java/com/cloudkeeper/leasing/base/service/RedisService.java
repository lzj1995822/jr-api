package com.cloudkeeper.leasing.base.service;

import com.cloudkeeper.leasing.base.constant.AuthorizationConstants;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import javax.annotation.Nonnull;

/**
 * redis service
 * @author jerry
 */
public interface RedisService {

    /**
     * 创建 token 并添加到 redis
     * @param id 用户id
     * @return token
     */
    @CachePut(value = AuthorizationConstants.REDIS_TOKEN_KEY, key = "#id")
    String putToken(@Nonnull String id);

    /**
     * 从 redis 查询 token
     * @param id 用户id
     * @return token
     */
    @Cacheable(value = AuthorizationConstants.REDIS_TOKEN_KEY, key = "#id")
    String getToken(@Nonnull String id);
}
