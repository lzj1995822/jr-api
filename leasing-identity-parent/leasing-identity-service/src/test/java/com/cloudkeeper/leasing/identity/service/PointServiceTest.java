package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.identity.domain.Point;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 实践点 service 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PointServiceTest {

    /** 实践点 service */
    @Autowired
    private PointService pointService;

    @Before
    public void setUp() {
        Point point = new Point();
        point = pointService.save(point);
    }

    @After
    public void tearDown() {
        pointService.deleteAll();
    }

    @Test
    public void save() {
        Point point = pointService.save(new Point());
        assertThat(point).isNotNull();
    }

}