package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.identity.domain.Menu;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 菜单 service 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuServiceTest {

    /** 菜单 service */
    @Autowired
    private MenuService menuService;

    @Before
    public void setUp() {
        Menu menu = new Menu();
        menu = menuService.save(menu);
    }

    @After
    public void tearDown() {
        menuService.deleteAll();
    }

    @Test
    public void save() {
        Menu menu = menuService.save(new Menu());
        assertThat(menu).isNotNull();
    }

}