package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.identity.domain.ActivityHistory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 活动记录 service 测试
 * @author hf
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityHistoryServiceTest {

    /** 活动记录 service */
    @Autowired
    private ActivityHistoryService activityHistoryService;

    @Before
    public void setUp() {
        ActivityHistory activityHistory = new ActivityHistory();
        activityHistory = activityHistoryService.save(activityHistory);
    }

    @After
    public void tearDown() {
        activityHistoryService.deleteAll();
    }

    @Test
    public void save() {
        ActivityHistory activityHistory = activityHistoryService.save(new ActivityHistory());
        assertThat(activityHistory).isNotNull();
    }

}