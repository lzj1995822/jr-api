package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.Menu;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 菜单 repository 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class MenuRepositoryTest {

    /** 测试 entityManager */
    @Autowired
    private TestEntityManager entityManager;

    /** 菜单 repository */
    @Autowired
    private MenuRepository menuRepository;

    @Before
    public void setUp() {
        Menu menu = new Menu();
        menu = entityManager.persist(menu);
    }

    @After
    public void tearDown() {
        menuRepository.deleteAll();
    }

    @Test
    public void save() {
        Menu menu = menuRepository.save(new Menu());
        assertThat(menu).isNotNull();
    }

}