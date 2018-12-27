package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.Center;
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
 * 分中心 repository 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CenterRepositoryTest {

    /** 测试 entityManager */
    @Autowired
    private TestEntityManager entityManager;

    /** 分中心 repository */
    @Autowired
    private CenterRepository centerRepository;

    @Before
    public void setUp() {
        Center center = new Center();
        center = entityManager.persist(center);
    }

    @After
    public void tearDown() {
        centerRepository.deleteAll();
    }

    @Test
    public void save() {
        Center center = centerRepository.save(new Center());
        assertThat(center).isNotNull();
    }

}