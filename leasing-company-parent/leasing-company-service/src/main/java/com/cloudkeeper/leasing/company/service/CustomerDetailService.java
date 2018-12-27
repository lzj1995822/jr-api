package com.cloudkeeper.leasing.company.service;

import com.cloudkeeper.leasing.base.domain.BaseEntity;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.company.domain.CustomerDetail;
import com.cloudkeeper.leasing.company.dto.customerdetail.CustomerDetailDTO;

import java.util.List;

/**
 * 客户详细、开票资料 service
 * @author asher
 */
public interface CustomerDetailService extends BaseService<CustomerDetail> {

    /**
     * 批量保存
     * @param customerDetailList
     * @param baseEntity
     * @return
     */
    List<CustomerDetail> saveList(List<CustomerDetailDTO> customerDetailList, BaseEntity baseEntity, Boolean isAdd);

    /**
     * 根绝客户id查找所有相关客户详细记录包括开票资料
     * @param id
     * @return
     */
    List<CustomerDetail> findAllByCustomerId(String id);
}