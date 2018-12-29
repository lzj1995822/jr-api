package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.identity.domain.OrgCenter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 分中心 service 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrgCenterServiceTest {

    /** 分中心 service */
    @Autowired
    private OrgCenterService orgCenterService;

    @Before
    public void setUp() {
        OrgCenter orgCenter = new OrgCenter();
        orgCenter = orgCenterService.save(orgCenter);
    }

    @After
    public void tearDown() {
        orgCenterService.deleteAll();
    }

    @Test
    public void save() {
        OrgCenter orgCenter = orgCenterService.save(new OrgCenter());
        assertThat(orgCenter).isNotNull();
    }

}