package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.identity.service.RoleMenuService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * 组织菜单 controller 测试
 * @author jerry
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoleMenuControllerTest {

    /** rest 请求工具 */
    @Autowired
    private TestRestTemplate restTemplate;

    /** 组织菜单 service */
    @MockBean
    private RoleMenuService roleMenuService;

    private String id = UUID.randomUUID().toString();

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void findOne() {
    }

}