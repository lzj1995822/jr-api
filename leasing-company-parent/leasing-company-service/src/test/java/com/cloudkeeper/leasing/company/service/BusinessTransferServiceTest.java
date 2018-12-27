package com.cloudkeeper.leasing.company.service;

import com.cloudkeeper.leasing.company.domain.BusinessTransfer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 业务员转移、转移客户主记录 service 测试
 * @author asher
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BusinessTransferServiceTest {

    /** 业务员转移、转移客户主记录 service */
    @Autowired
    private BusinessTransferService businessTransferService;

    @Test
    public void saveTest() {
        BusinessTransfer businessTransfer = new BusinessTransfer();
        businessTransfer = businessTransferService.save(businessTransfer);
        assertNotNull(businessTransfer.getId());
    }

}