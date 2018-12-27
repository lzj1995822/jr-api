package com.cloudkeeper.leasing.company.repository;

import com.cloudkeeper.leasing.company.domain.BusinessTransfer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 业务员转移、转移客户主记录 repository 测试
 * @author asher
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BusinessTransferRepositoryTest {

    /** 业务员转移、转移客户主记录 repository */
    @Autowired
    private BusinessTransferRepository businessTransferRepository;

    @Test
    public void saveTest() {
        BusinessTransfer businessTransfer = new BusinessTransfer();
        businessTransfer = businessTransferRepository.save(businessTransfer);
        assertNotNull(businessTransfer.getId());
    }

}