package com.cloudkeeper.leasing.identity.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.identity.domain.Town;
import com.cloudkeeper.leasing.identity.repository.TownRepository;
import com.cloudkeeper.leasing.identity.service.TownService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

/**
 * 所站 service
 * @author wj
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TownServiceImpl extends BaseServiceImpl<Town> implements TownService {

    /** 所站 repository */
    private final TownRepository townRepository;

    @Override
    protected BaseRepository<Town> getBaseRepository() {
        return townRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("coding", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("townName", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("longitude", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("latitude", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("remark", ExampleMatcher.GenericPropertyMatchers.contains());
    }

}