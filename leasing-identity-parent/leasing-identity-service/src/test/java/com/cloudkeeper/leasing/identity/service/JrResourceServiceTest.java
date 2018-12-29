package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.identity.domain.JrResource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 文件上传 service 测试
 * @author hf
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JrResourceServiceTest {

    /** 文件上传 service */
    @Autowired
    private JrResourceService jrResourceService;

    @Before
    public void setUp() {
        JrResource jrResource = new JrResource();
        jrResource = jrResourceService.save(jrResource);
    }

    @After
    public void tearDown() {
        jrResourceService.deleteAll();
    }

    @Test
    public void save() {
        JrResource jrResource = jrResourceService.save(new JrResource());
        assertThat(jrResource).isNotNull();
    }

}