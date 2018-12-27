package com.cloudkeeper.leasing.base.service.impl;

import com.cloudkeeper.leasing.base.service.RedisService;
import com.cloudkeeper.leasing.base.utils.TokenUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;

/**
 * redis service
 * @author jerry
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Override
    public String putToken(@Nonnull String id) {
        return TokenUtil.of(id);
    }

    @Override
    public String getToken(@Nonnull String id) {
        return TokenUtil.of(id);
    }
}
