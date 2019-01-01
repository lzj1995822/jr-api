package com.cloudkeeper.leasing.identity.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.identity.domain.RecordSpecial;
import com.cloudkeeper.leasing.identity.repository.RecordSpecialRepository;
import com.cloudkeeper.leasing.identity.service.RecordSpecialService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

/**
 * 特殊活动 service
 * @author hf
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecordSpecialServiceImpl extends BaseServiceImpl<RecordSpecial> implements RecordSpecialService {

    /** 特殊活动 repository */
    private final RecordSpecialRepository recordSpecialRepository;

    @Override
    protected BaseRepository<RecordSpecial> getBaseRepository() {
        return recordSpecialRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("status", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("content", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("opinion", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("volunteers", ExampleMatcher.GenericPropertyMatchers.contains());
    }

}