package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.identity.domain.Activity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 活动 service 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityServiceTest {

    /** 活动 service */
    @Autowired
    private ActivityService activityService;

    @Before
    public void setUp() {
        Activity activity = new Activity();
        activity = activityService.save(activity);
    }

    @After
    public void tearDown() {
        activityService.deleteAll();
    }

    @Test
    public void save() {
        Activity activity = activityService.save(new Activity());
        assertThat(activity).isNotNull();
    }

}