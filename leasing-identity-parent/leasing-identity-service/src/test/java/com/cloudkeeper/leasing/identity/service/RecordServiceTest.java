package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.identity.domain.Record;
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
 * @author wj
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RecordServiceTest {

    /** 活动记录 service */
    @Autowired
    private RecordService recordService;

    @Before
    public void setUp() {
        Record record = new Record();
        record = recordService.save(record);
    }

    @After
    public void tearDown() {
        recordService.deleteAll();
    }

    @Test
    public void save() {
        Record record = recordService.save(new Record());
        assertThat(record).isNotNull();
    }

}