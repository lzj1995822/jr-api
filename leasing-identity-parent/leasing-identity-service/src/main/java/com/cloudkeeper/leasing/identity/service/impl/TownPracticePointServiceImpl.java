package com.cloudkeeper.leasing.identity.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.identity.domain.TownPracticePoint;
import com.cloudkeeper.leasing.identity.repository.TownPracticePointRepository;
import com.cloudkeeper.leasing.identity.service.TownPracticePointService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

/**
 * 镇文明实践点 service
 * @author wj
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TownPracticePointServiceImpl extends BaseServiceImpl<TownPracticePoint> implements TownPracticePointService {

    /** 镇文明实践点 repository */
    private final TownPracticePointRepository townPracticePointRepository;

    @Override
    protected BaseRepository<TownPracticePoint> getBaseRepository() {
        return townPracticePointRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("longitude", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("latitude", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("remark", ExampleMatcher.GenericPropertyMatchers.contains());
    }

}