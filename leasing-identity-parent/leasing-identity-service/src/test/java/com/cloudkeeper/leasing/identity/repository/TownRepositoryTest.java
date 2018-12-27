package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.Town;
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
 * 所站 repository 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class TownRepositoryTest {

    /** 测试 entityManager */
    @Autowired
    private TestEntityManager entityManager;

    /** 所站 repository */
    @Autowired
    private TownRepository townRepository;

    @Before
    public void setUp() {
        Town town = new Town();
        town = entityManager.persist(town);
    }

    @After
    public void tearDown() {
        townRepository.deleteAll();
    }

    @Test
    public void save() {
        Town town = townRepository.save(new Town());
        assertThat(town).isNotNull();
    }

}