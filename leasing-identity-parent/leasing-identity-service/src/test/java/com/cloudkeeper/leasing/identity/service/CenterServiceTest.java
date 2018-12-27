package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.identity.domain.Center;
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
public class CenterServiceTest {

    /** 分中心 service */
    @Autowired
    private CenterService centerService;

    @Before
    public void setUp() {
        Center center = new Center();
        center = centerService.save(center);
    }

    @After
    public void tearDown() {
        centerService.deleteAll();
    }

    @Test
    public void save() {
        Center center = centerService.save(new Center());
        assertThat(center).isNotNull();
    }

}