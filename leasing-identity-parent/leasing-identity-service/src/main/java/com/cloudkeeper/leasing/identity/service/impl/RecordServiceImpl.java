package com.cloudkeeper.leasing.identity.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.identity.domain.Record;
import com.cloudkeeper.leasing.identity.domain.RecordHistory;
import com.cloudkeeper.leasing.identity.repository.RecordRepository;
import com.cloudkeeper.leasing.identity.service.RecordHistoryService;
import com.cloudkeeper.leasing.identity.service.RecordService;
import lombok.RequiredArgsConstructor;

import javax.annotation.Nonnull;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

/**
 * 活动记录 service
 * @author wj
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecordServiceImpl extends BaseServiceImpl<Record> implements RecordService {

    /** 活动记录 repository */
    private final RecordRepository recordRepository;
    
    private final RecordHistoryService recordHistoryService;

    @Override
    protected BaseRepository<Record> getBaseRepository() {
        return recordRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("status", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("content", ExampleMatcher.GenericPropertyMatchers.contains());
    }

    @Nonnull
	@Override
	public Record save(Record entity) {
		RecordHistory recordHistory = new RecordHistory();
		BeanUtils.copyProperties(entity, recordHistory, "id");
		entity = super.save(entity);
		recordHistory.setRecordId(entity.getId());
		recordHistoryService.save(recordHistory);
		return entity;
	}
    
}