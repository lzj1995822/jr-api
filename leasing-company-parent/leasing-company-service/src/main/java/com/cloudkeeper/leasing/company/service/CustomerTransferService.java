package com.cloudkeeper.leasing.company.service;

import com.cloudkeeper.leasing.company.domain.BusinessTransfer;
import com.cloudkeeper.leasing.company.domain.CustomerTransfer;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.company.dto.businesstransfer.BusinessTransferDTO;

import java.util.List;

/**
 * 转移d的客户 service
 * @author asher
 */
public interface CustomerTransferService extends BaseService<CustomerTransfer> {

    /**
     * 批量保存转移客户
     * @param businessTransferDTO
     * @param businessTransfer
     * @return
     */
    List<CustomerTransfer> saveList(BusinessTransferDTO businessTransferDTO, BusinessTransfer businessTransfer);
}