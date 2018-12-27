package com.cloudkeeper.leasing.company.repository;

import com.cloudkeeper.leasing.company.domain.CustomerTransfer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 转移d的客户 repository 测试
 * @author asher
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerTransferRepositoryTest {

    /** 转移d的客户 repository */
    @Autowired
    private CustomerTransferRepository customerTransferRepository;

    @Test
    public void saveTest() {
        CustomerTransfer customerTransfer = new CustomerTransfer();
        customerTransfer = customerTransferRepository.save(customerTransfer);
        assertNotNull(customerTransfer.getId());
    }

}