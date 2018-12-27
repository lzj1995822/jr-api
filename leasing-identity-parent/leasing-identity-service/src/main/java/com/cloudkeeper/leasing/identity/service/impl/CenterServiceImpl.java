package com.cloudkeeper.leasing.identity.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.identity.domain.Center;
import com.cloudkeeper.leasing.identity.repository.CenterRepository;
import com.cloudkeeper.leasing.identity.service.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

/**
 * 分中心 service
 * @author wj
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CenterServiceImpl extends BaseServiceImpl<Center> implements CenterService {

    /** 分中心 repository */
    private final CenterRepository centerRepository;

    @Override
    protected BaseRepository<Center> getBaseRepository() {
        return centerRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("coding", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("culturalType", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("longitude", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("latitude", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("remark", ExampleMatcher.GenericPropertyMatchers.contains());
    }

}