package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.Activity;
import com.cloudkeeper.leasing.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * 活动 repository
 * @author wj
 */
@Repository
public interface ActivityRepository extends BaseRepository<Activity> {
	
}