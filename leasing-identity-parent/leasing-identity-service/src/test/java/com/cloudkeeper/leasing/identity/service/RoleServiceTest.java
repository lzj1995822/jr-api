package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.identity.domain.Role;
import com.cloudkeeper.leasing.identity.domain.RoleMenu;
import com.cloudkeeper.leasing.identity.vo.RoleVO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 角色 service 测试
 * @author jerry
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleServiceTest {

    /** 角色 service */
    @Autowired
    private RoleService roleService;

    /** 角色菜单 service */
    @Autowired
    private RoleMenuService roleMenuService;

    private static final String TEST_CODE = "test";
    private Role role;

    @Before
    public void setUp() {
        role = roleService.save(new Role().setCode(TEST_CODE));
        roleMenuService.save(new RoleMenu().setRoleId(role.getId()).setMenuCode("aa"));
    }

    @After
    public void tearDown() {
        roleMenuService.deleteAll();
        roleService.deleteAll();
    }

    @Test
    public void save() {
        Role role = roleService.save(new Role());
        assertThat(role).isNotNull();
    }

    @Test
    public void existsCode() {
        assertThat(roleService.existsCode(TEST_CODE, role.getId() + "1")).isTrue();
        assertThat(roleService.existsCode(TEST_CODE, role.getId())).isFalse();
        assertThat(roleService.existsCode(TEST_CODE, null)).isTrue();
        assertThat(roleService.existsCode(TEST_CODE + "1", null)).isFalse();
    }

    @Test
    public void loadChildrenVO() {
        RoleVO roleVO = new RoleVO();
        roleVO.setId(role.getId());
        roleService.loadChildrenVO(roleVO);
        assertThat(roleVO.getRoleMenuVOList()).isNotEmpty();
    }
}