package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.CenterUser;
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
 * 分中心人员 repository 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CenterUserRepositoryTest {

    /** 测试 entityManager */
    @Autowired
    private TestEntityManager entityManager;

    /** 分中心人员 repository */
    @Autowired
    private CenterUserRepository centerUserRepository;

    @Before
    public void setUp() {
        CenterUser centerUser = new CenterUser();
        centerUser = entityManager.persist(centerUser);
    }

    @After
    public void tearDown() {
        centerUserRepository.deleteAll();
    }

    @Test
    public void save() {
        CenterUser centerUser = centerUserRepository.save(new CenterUser());
        assertThat(centerUser).isNotNull();
    }

}