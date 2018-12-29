package com.cloudkeeper.leasing.identity.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.identity.domain.Activity;
import com.cloudkeeper.leasing.identity.repository.ActivityRepository;
import com.cloudkeeper.leasing.identity.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

/**
 * 活动 service
 * @author wj
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ActivityServiceImpl extends BaseServiceImpl<Activity> implements ActivityService {

    /** 活动 repository */
    private final ActivityRepository activityRepository;

    @Override
    protected BaseRepository<Activity> getBaseRepository() {
        return activityRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("des", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("url", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("type", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("status", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("isSpecial", ExampleMatcher.GenericPropertyMatchers.contains());
    }

}