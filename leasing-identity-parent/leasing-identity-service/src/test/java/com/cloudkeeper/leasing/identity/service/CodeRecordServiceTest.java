package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.identity.domain.CodeRecord;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 编码生成记录 service 测试
 * @author asher
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CodeRecordServiceTest {

    /** 编码生成记录 service */
    @Autowired
    private CodeRecordService codeRecordService;

    @Before
    public void setUp() {
        CodeRecord codeRecord = new CodeRecord();
        codeRecord = codeRecordService.save(codeRecord);
    }

    @After
    public void tearDown() {
        codeRecordService.deleteAll();
    }

    @Test
    public void save() {
        CodeRecord codeRecord = codeRecordService.save(new CodeRecord());
        assertThat(codeRecord).isNotNull();
    }

}