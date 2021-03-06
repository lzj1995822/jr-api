package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.TownUser;
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
 * 所站人员 repository 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class TownUserRepositoryTest {

    /** 测试 entityManager */
    @Autowired
    private TestEntityManager entityManager;

    /** 所站人员 repository */
    @Autowired
    private TownUserRepository townUserRepository;

    @Before
    public void setUp() {
        TownUser townUser = new TownUser();
        townUser = entityManager.persist(townUser);
    }

    @After
    public void tearDown() {
        townUserRepository.deleteAll();
    }

    @Test
    public void save() {
        TownUser townUser = townUserRepository.save(new TownUser());
        assertThat(townUser).isNotNull();
    }

}