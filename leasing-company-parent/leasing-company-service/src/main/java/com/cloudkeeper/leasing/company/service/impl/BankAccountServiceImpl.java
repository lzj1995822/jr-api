package com.cloudkeeper.leasing.company.service.impl;

import com.cloudkeeper.leasing.base.domain.BaseEntity;
import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.company.domain.BankAccount;
import com.cloudkeeper.leasing.company.dto.bankaccount.BankAccountDTO;
import com.cloudkeeper.leasing.company.repository.BankAccountRepository;
import com.cloudkeeper.leasing.company.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 银行账号 service
 * @author asher
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BankAccountServiceImpl extends BaseServiceImpl<BankAccount> implements BankAccountService {

    /** 银行账号 repository */
    private final BankAccountRepository bankAccountRepository;

    @Override
    protected BaseRepository<BankAccount> getBaseRepository() {
        return bankAccountRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("account", ExampleMatcher.GenericPropertyMatchers.contains());
    }

    @Override
    public List<BankAccount> saveList(List<BankAccountDTO> bankAccountList, BaseEntity baseEntity, Boolean isAdd) {
        List<BankAccount> bankAccounts = new ArrayList<>();
        bankAccountList.forEach(bankAccountDTO -> {
            BankAccount bankAccount = bankAccountDTO.convert(BankAccount.class);
            bankAccount.setCustomerId(baseEntity.getId());
             //父新增 +  子新增 -> version不加1      父更新 + 子增 -> 子version加1
            if (StringUtils.isEmpty(bankAccount.getId()) && !isAdd) {
                bankAccount.setVersion(baseEntity.getVersion() + 1);
            }
            bankAccount = save(bankAccount);
            bankAccounts.add(bankAccount);
        });
        return bankAccounts;
    }

    @Override
    public List<BankAccount> findAllByCustomerId(String id) {
        return bankAccountRepository.findAllByCustomerId(id);
    }
}