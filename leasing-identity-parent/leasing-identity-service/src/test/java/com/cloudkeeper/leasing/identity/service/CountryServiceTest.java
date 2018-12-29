package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.identity.domain.Country;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 村 service 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CountryServiceTest {

    /** 村 service */
    @Autowired
    private CountryService countryService;

    @Before
    public void setUp() {
        Country country = new Country();
        country = countryService.save(country);
    }

    @After
    public void tearDown() {
        countryService.deleteAll();
    }

    @Test
    public void save() {
        Country country = countryService.save(new Country());
        assertThat(country).isNotNull();
    }

}