package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.identity.domain.CenterUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 分中心人员 service 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CenterUserServiceTest {

    /** 分中心人员 service */
    @Autowired
    private CenterUserService centerUserService;

    @Before
    public void setUp() {
        CenterUser centerUser = new CenterUser();
        centerUser = centerUserService.save(centerUser);
    }

    @After
    public void tearDown() {
        centerUserService.deleteAll();
    }

    @Test
    public void save() {
        CenterUser centerUser = centerUserService.save(new CenterUser());
        assertThat(centerUser).isNotNull();
    }

}