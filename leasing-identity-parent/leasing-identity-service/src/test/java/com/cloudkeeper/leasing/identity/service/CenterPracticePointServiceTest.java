package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.identity.domain.CenterPracticePoint;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 分中心文明实践点 service 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CenterPracticePointServiceTest {

    /** 分中心文明实践点 service */
    @Autowired
    private CenterPracticePointService centerPracticePointService;

    @Before
    public void setUp() {
        CenterPracticePoint centerPracticePoint = new CenterPracticePoint();
        centerPracticePoint = centerPracticePointService.save(centerPracticePoint);
    }

    @After
    public void tearDown() {
        centerPracticePointService.deleteAll();
    }

    @Test
    public void save() {
        CenterPracticePoint centerPracticePoint = centerPracticePointService.save(new CenterPracticePoint());
        assertThat(centerPracticePoint).isNotNull();
    }

}