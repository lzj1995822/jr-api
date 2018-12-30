package com.cloudkeeper.leasing.identity.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.identity.domain.ActivityHistory;
import com.cloudkeeper.leasing.identity.repository.ActivityHistoryRepository;
import com.cloudkeeper.leasing.identity.service.ActivityHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

/**
 * 活动记录 service
 * @author hf
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ActivityHistoryServiceImpl extends BaseServiceImpl<ActivityHistory> implements ActivityHistoryService {

    /** 活动记录 repository */
    private final ActivityHistoryRepository activityHistoryRepository;

    @Override
    protected BaseRepository<ActivityHistory> getBaseRepository() {
        return activityHistoryRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("des", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("url", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("type", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("status", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("activityType", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("activityid", ExampleMatcher.GenericPropertyMatchers.contains());
    }

}