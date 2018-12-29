package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.OrgPerson;
import com.cloudkeeper.leasing.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * 组织架构人员 repository
 * @author wj
 */
@Repository
public interface OrgPersonRepository extends BaseRepository<OrgPerson> {

}