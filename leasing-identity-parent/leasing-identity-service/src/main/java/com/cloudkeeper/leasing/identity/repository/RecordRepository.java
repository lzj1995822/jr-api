package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.Record;
import com.cloudkeeper.leasing.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * 活动记录 repository
 * @author wj
 */
@Repository
public interface RecordRepository extends BaseRepository<Record> {
	
}