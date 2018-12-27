package com.cloudkeeper.leasing.company.repository;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.company.domain.BankAccount;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 银行账号 repository
 * @author asher
 */
@Repository
public interface BankAccountRepository extends BaseRepository<BankAccount> {

    /**
     * 根据客户id查找所有银行账号信息记录
     * @param id
     * @return
     */
    List<BankAccount> findAllByCustomerId(String id);
}