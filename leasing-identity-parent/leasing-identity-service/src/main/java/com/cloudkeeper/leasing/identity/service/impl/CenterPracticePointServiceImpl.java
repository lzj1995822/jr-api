package com.cloudkeeper.leasing.identity.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.identity.domain.CenterPracticePoint;
import com.cloudkeeper.leasing.identity.repository.CenterPracticePointRepository;
import com.cloudkeeper.leasing.identity.service.CenterPracticePointService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

/**
 * 分中心文明实践点 service
 * @author wj
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CenterPracticePointServiceImpl extends BaseServiceImpl<CenterPracticePoint> implements CenterPracticePointService {

    /** 分中心文明实践点 repository */
    private final CenterPracticePointRepository centerPracticePointRepository;

    @Override
    protected BaseRepository<CenterPracticePoint> getBaseRepository() {
        return centerPracticePointRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("longitude", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("latitude", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("remark", ExampleMatcher.GenericPropertyMatchers.contains());
    }

}