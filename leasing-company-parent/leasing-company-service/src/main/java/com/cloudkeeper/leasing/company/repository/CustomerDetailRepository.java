package com.cloudkeeper.leasing.company.repository;

import com.cloudkeeper.leasing.company.domain.CustomerDetail;
import com.cloudkeeper.leasing.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 客户详细、开票资料 repository
 * @author asher
 */
@Repository
public interface CustomerDetailRepository extends BaseRepository<CustomerDetail> {

    /**
     * 根绝客户id查找所有相关客户详细记录包括开票资料
     * @param id
     * @return
     */
    List<CustomerDetail> findAllByCustomerId(String id);
}