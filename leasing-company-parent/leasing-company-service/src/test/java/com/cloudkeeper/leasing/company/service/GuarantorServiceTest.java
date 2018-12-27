package com.cloudkeeper.leasing.company.service;

import com.cloudkeeper.leasing.company.domain.Guarantor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 担保人 service 测试
 * @author asher
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class GuarantorServiceTest {

    /** 担保人 service */
    @Autowired
    private GuarantorService guarantorService;

    @Test
    public void saveTest() {
        Guarantor guarantor = new Guarantor();
        guarantor = guarantorService.save(guarantor);
        assertNotNull(guarantor.getId());
    }

}