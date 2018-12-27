package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.identity.domain.Town;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 所站 service 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TownServiceTest {

    /** 所站 service */
    @Autowired
    private TownService townService;

    @Before
    public void setUp() {
        Town town = new Town();
        town = townService.save(town);
    }

    @After
    public void tearDown() {
        townService.deleteAll();
    }

    @Test
    public void save() {
        Town town = townService.save(new Town());
        assertThat(town).isNotNull();
    }

}