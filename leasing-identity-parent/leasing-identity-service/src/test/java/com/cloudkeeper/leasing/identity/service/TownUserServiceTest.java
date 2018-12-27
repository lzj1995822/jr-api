package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.identity.domain.TownUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 所站人员 service 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TownUserServiceTest {

    /** 所站人员 service */
    @Autowired
    private TownUserService townUserService;

    @Before
    public void setUp() {
        TownUser townUser = new TownUser();
        townUser = townUserService.save(townUser);
    }

    @After
    public void tearDown() {
        townUserService.deleteAll();
    }

    @Test
    public void save() {
        TownUser townUser = townUserService.save(new TownUser());
        assertThat(townUser).isNotNull();
    }

}