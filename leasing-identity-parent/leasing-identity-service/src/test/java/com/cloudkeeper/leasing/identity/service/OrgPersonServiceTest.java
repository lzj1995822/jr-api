package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.identity.domain.OrgPerson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 组织架构人员 service 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrgPersonServiceTest {

    /** 组织架构人员 service */
    @Autowired
    private OrgPersonService orgPersonService;

    @Before
    public void setUp() {
        OrgPerson orgPerson = new OrgPerson();
        orgPerson = orgPersonService.save(orgPerson);
    }

    @After
    public void tearDown() {
        orgPersonService.deleteAll();
    }

    @Test
    public void save() {
        OrgPerson orgPerson = orgPersonService.save(new OrgPerson());
        assertThat(orgPerson).isNotNull();
    }

}