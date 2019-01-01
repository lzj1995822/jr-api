package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.identity.domain.RecordSpecial;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 特殊活动 service 测试
 * @author hf
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RecordSpecialServiceTest {

    /** 特殊活动 service */
    @Autowired
    private RecordSpecialService recordSpecialService;

    @Before
    public void setUp() {
        RecordSpecial recordSpecial = new RecordSpecial();
        recordSpecial = recordSpecialService.save(recordSpecial);
    }

    @After
    public void tearDown() {
        recordSpecialService.deleteAll();
    }

    @Test
    public void save() {
        RecordSpecial recordSpecial = recordSpecialService.save(new RecordSpecial());
        assertThat(recordSpecial).isNotNull();
    }

}