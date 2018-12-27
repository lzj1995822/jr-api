package com.cloudkeeper.leasing.company.service;

import com.cloudkeeper.leasing.base.domain.BaseEntity;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.company.domain.BankAccount;
import com.cloudkeeper.leasing.company.dto.bankaccount.BankAccountDTO;

import java.util.List;

/**
 * 银行账号 service
 * @author asher
 */
public interface BankAccountService extends BaseService<BankAccount> {

    /**
     * 批量保存银行账号
     * @param bankAccountList
     * @param baseEntity
     * @return
     */
    List<BankAccount> saveList(List<BankAccountDTO> bankAccountList, BaseEntity baseEntity, Boolean isAdd);

    /**
     * 根据客户id查找所有银行账号信息记录
     * @param id
     * @return
     */
    List<BankAccount> findAllByCustomerId(String id);
}