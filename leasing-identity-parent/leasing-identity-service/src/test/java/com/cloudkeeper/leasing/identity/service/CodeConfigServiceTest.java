package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.identity.domain.CodeConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 编码配置 service 测试
 * @author asher
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CodeConfigServiceTest {

    /** 编码配置 service */
    @Autowired
    private CodeConfigService codeConfigService;

    @Before
    public void setUp() {
        CodeConfig codeConfig = new CodeConfig();
        codeConfig = codeConfigService.save(codeConfig);
    }

    @After
    public void tearDown() {
        codeConfigService.deleteAll();
    }

    @Test
    public void save() {
        CodeConfig codeConfig = codeConfigService.save(new CodeConfig());
        assertThat(codeConfig).isNotNull();
    }

}