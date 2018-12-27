package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.TownUser;
import com.cloudkeeper.leasing.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * 所站人员 repository
 * @author wj
 */
@Repository
public interface TownUserRepository extends BaseRepository<TownUser> {

}