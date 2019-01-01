package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.TownActivity;
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
 * 镇活动 repository 测试
 * @author hf
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class TownActivityRepositoryTest {

    /** 测试 entityManager */
    @Autowired
    private TestEntityManager entityManager;

    /** 镇活动 repository */
    @Autowired
    private TownActivityRepository townActivityRepository;

    @Before
    public void setUp() {
        TownActivity townActivity = new TownActivity();
        townActivity = entityManager.persist(townActivity);
    }

    @After
    public void tearDown() {
        townActivityRepository.deleteAll();
    }

    @Test
    public void save() {
        TownActivity townActivity = townActivityRepository.save(new TownActivity());
        assertThat(townActivity).isNotNull();
    }

}