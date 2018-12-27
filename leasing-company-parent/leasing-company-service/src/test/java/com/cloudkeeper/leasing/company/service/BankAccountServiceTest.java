package com.cloudkeeper.leasing.company.service;

import com.cloudkeeper.leasing.company.domain.BankAccount;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 银行账号 service 测试
 * @author asher
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BankAccountServiceTest {

    /** 银行账号 service */
    @Autowired
    private BankAccountService bankAccountService;

    @Test
    public void saveTest() {
        BankAccount bankAccount = new BankAccount();
        bankAccount = bankAccountService.save(bankAccount);
        assertNotNull(bankAccount.getId());
    }

}