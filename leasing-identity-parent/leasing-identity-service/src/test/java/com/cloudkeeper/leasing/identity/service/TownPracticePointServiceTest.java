package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.identity.domain.TownPracticePoint;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 镇文明实践点 service 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TownPracticePointServiceTest {

    /** 镇文明实践点 service */
    @Autowired
    private TownPracticePointService townPracticePointService;

    @Before
    public void setUp() {
        TownPracticePoint townPracticePoint = new TownPracticePoint();
        townPracticePoint = townPracticePointService.save(townPracticePoint);
    }

    @After
    public void tearDown() {
        townPracticePointService.deleteAll();
    }

    @Test
    public void save() {
        TownPracticePoint townPracticePoint = townPracticePointService.save(new TownPracticePoint());
        assertThat(townPracticePoint).isNotNull();
    }

}