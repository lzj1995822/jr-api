package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.identity.domain.TownFunctionRoom;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 所站功能室建设 service 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TownFunctionRoomServiceTest {

    /** 所站功能室建设 service */
    @Autowired
    private TownFunctionRoomService townFunctionRoomService;

    @Before
    public void setUp() {
        TownFunctionRoom townFunctionRoom = new TownFunctionRoom();
        townFunctionRoom = townFunctionRoomService.save(townFunctionRoom);
    }

    @After
    public void tearDown() {
        townFunctionRoomService.deleteAll();
    }

    @Test
    public void save() {
        TownFunctionRoom townFunctionRoom = townFunctionRoomService.save(new TownFunctionRoom());
        assertThat(townFunctionRoom).isNotNull();
    }

}