package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.identity.domain.RecordHistory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 活动记录历史 service 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RecordHistoryServiceTest {

    /** 活动记录历史 service */
    @Autowired
    private RecordHistoryService recordHistoryService;

    @Before
    public void setUp() {
        RecordHistory recordHistory = new RecordHistory();
        recordHistory = recordHistoryService.save(recordHistory);
    }

    @After
    public void tearDown() {
        recordHistoryService.deleteAll();
    }

    @Test
    public void save() {
        RecordHistory recordHistory = recordHistoryService.save(new RecordHistory());
        assertThat(recordHistory).isNotNull();
    }

}