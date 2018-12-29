package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.identity.domain.OrgRoom;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 功能室 service 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrgRoomServiceTest {

    /** 功能室 service */
    @Autowired
    private OrgRoomService orgRoomService;

    @Before
    public void setUp() {
        OrgRoom orgRoom = new OrgRoom();
        orgRoom = orgRoomService.save(orgRoom);
    }

    @After
    public void tearDown() {
        orgRoomService.deleteAll();
    }

    @Test
    public void save() {
        OrgRoom orgRoom = orgRoomService.save(new OrgRoom());
        assertThat(orgRoom).isNotNull();
    }

}