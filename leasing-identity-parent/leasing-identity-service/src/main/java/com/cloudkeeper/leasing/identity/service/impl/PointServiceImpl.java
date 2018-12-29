package com.cloudkeeper.leasing.identity.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.identity.domain.Point;
import com.cloudkeeper.leasing.identity.repository.PointRepository;
import com.cloudkeeper.leasing.identity.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

/**
 * 实践点 service
 * @author wj
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PointServiceImpl extends BaseServiceImpl<Point> implements PointService {

    /** 实践点 repository */
    private final PointRepository pointRepository;

    @Override
    protected BaseRepository<Point> getBaseRepository() {
        return pointRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("des", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("type", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("orgType", ExampleMatcher.GenericPropertyMatchers.contains());
    }

}