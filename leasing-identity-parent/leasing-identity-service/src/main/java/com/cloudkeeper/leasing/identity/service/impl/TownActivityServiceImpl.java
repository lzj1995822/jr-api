package com.cloudkeeper.leasing.identity.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.identity.domain.TownActivity;
import com.cloudkeeper.leasing.identity.repository.TownActivityRepository;
import com.cloudkeeper.leasing.identity.service.TownActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

/**
 * 镇活动 service
 * @author hf
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TownActivityServiceImpl extends BaseServiceImpl<TownActivity> implements TownActivityService {

    /** 镇活动 repository */
    private final TownActivityRepository townActivityRepository;

    @Override
    protected BaseRepository<TownActivity> getBaseRepository() {
        return townActivityRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher();
    }

}