package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.identity.domain.FunctionRoomUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 功能室人员 service 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FunctionRoomUserServiceTest {

    /** 功能室人员 service */
    @Autowired
    private FunctionRoomUserService functionRoomUserService;

    @Before
    public void setUp() {
        FunctionRoomUser functionRoomUser = new FunctionRoomUser();
        functionRoomUser = functionRoomUserService.save(functionRoomUser);
    }

    @After
    public void tearDown() {
        functionRoomUserService.deleteAll();
    }

    @Test
    public void save() {
        FunctionRoomUser functionRoomUser = functionRoomUserService.save(new FunctionRoomUser());
        assertThat(functionRoomUser).isNotNull();
    }

}