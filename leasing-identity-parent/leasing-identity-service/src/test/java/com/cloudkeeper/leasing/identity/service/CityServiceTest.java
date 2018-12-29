package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.identity.domain.City;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 市 service 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CityServiceTest {

    /** 市 service */
    @Autowired
    private CityService cityService;

    @Before
    public void setUp() {
        City city = new City();
        city = cityService.save(city);
    }

    @After
    public void tearDown() {
        cityService.deleteAll();
    }

    @Test
    public void save() {
        City city = cityService.save(new City());
        assertThat(city).isNotNull();
    }

}