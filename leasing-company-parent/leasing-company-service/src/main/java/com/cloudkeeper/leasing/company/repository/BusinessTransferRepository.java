package com.cloudkeeper.leasing.company.repository;

import com.cloudkeeper.leasing.company.domain.BusinessTransfer;
import com.cloudkeeper.leasing.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * 业务员转移、转移客户主记录 repository
 * @author asher
 */
@Repository
public interface BusinessTransferRepository extends BaseRepository<BusinessTransfer> {

}