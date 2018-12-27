package com.cloudkeeper.leasing.company.service;

import com.cloudkeeper.leasing.company.domain.IdentificationNumber;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 机器编号 service 测试
 * @author asher
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class IdentificationNumberServiceTest {

    /** 机器编号 service */
    @Autowired
    private IdentificationNumberService identificationNumberService;

    @Test
    public void saveTest() {
        IdentificationNumber identificationNumber = new IdentificationNumber();
        identificationNumber = identificationNumberService.save(identificationNumber);
        assertNotNull(identificationNumber.getId());
    }

}