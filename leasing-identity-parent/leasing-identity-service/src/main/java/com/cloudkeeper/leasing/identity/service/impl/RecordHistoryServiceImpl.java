package com.cloudkeeper.leasing.identity.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.identity.domain.RecordHistory;
import com.cloudkeeper.leasing.identity.repository.RecordHistoryRepository;
import com.cloudkeeper.leasing.identity.service.RecordHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

/**
 * 活动记录历史 service
 * @author wj
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecordHistoryServiceImpl extends BaseServiceImpl<RecordHistory> implements RecordHistoryService {

    /** 活动记录历史 repository */
    private final RecordHistoryRepository recordHistoryRepository;

    @Override
    protected BaseRepository<RecordHistory> getBaseRepository() {
        return recordHistoryRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("status", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("content", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("opinion", ExampleMatcher.GenericPropertyMatchers.contains());
    }

}