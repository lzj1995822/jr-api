package com.cloudkeeper.leasing.company.service;

import com.cloudkeeper.leasing.company.domain.SecuredAssets;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 担保资产 service 测试
 * @author asher
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SecuredAssetsServiceTest {

    /** 担保资产 service */
    @Autowired
    private SecuredAssetsService securedAssetsService;

    @Test
    public void saveTest() {
        SecuredAssets securedAssets = new SecuredAssets();
        securedAssets = securedAssetsService.save(securedAssets);
        assertNotNull(securedAssets.getId());
    }

}