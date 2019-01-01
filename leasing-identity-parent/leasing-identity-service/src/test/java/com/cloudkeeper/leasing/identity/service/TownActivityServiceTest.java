package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.identity.domain.TownActivity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 镇活动 service 测试
 * @author hf
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TownActivityServiceTest {

    /** 镇活动 service */
    @Autowired
    private TownActivityService townActivityService;

    @Before
    public void setUp() {
        TownActivity townActivity = new TownActivity();
        townActivity = townActivityService.save(townActivity);
    }

    @After
    public void tearDown() {
        townActivityService.deleteAll();
    }

    @Test
    public void save() {
        TownActivity townActivity = townActivityService.save(new TownActivity());
        assertThat(townActivity).isNotNull();
    }

}