package com.cloudkeeper.leasing.company.repository;

import com.cloudkeeper.leasing.company.domain.BankAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 银行账号 repository 测试
 * @author asher
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BankAccountRepositoryTest {

    /** 银行账号 repository */
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Test
    public void saveTest() {
        BankAccount bankAccount = new BankAccount();
        bankAccount = bankAccountRepository.save(bankAccount);
        assertNotNull(bankAccount.getId());
    }

}