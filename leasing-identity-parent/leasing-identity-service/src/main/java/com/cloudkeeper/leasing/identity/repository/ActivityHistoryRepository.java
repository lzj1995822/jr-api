package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.ActivityHistory;
import com.cloudkeeper.leasing.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * 活动记录 repository
 * @author hf
 */
@Repository
public interface ActivityHistoryRepository extends BaseRepository<ActivityHistory> {

}