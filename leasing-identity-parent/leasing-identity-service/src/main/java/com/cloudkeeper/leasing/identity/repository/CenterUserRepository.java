package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.CenterUser;
import com.cloudkeeper.leasing.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * 分中心人员 repository
 * @author wj
 */
@Repository
public interface CenterUserRepository extends BaseRepository<CenterUser> {

}