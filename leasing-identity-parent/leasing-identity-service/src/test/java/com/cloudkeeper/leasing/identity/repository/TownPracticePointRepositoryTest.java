package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.TownPracticePoint;
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
 * 镇文明实践点 repository 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class TownPracticePointRepositoryTest {

    /** 测试 entityManager */
    @Autowired
    private TestEntityManager entityManager;

    /** 镇文明实践点 repository */
    @Autowired
    private TownPracticePointRepository townPracticePointRepository;

    @Before
    public void setUp() {
        TownPracticePoint townPracticePoint = new TownPracticePoint();
        townPracticePoint = entityManager.persist(townPracticePoint);
    }

    @After
    public void tearDown() {
        townPracticePointRepository.deleteAll();
    }

    @Test
    public void save() {
        TownPracticePoint townPracticePoint = townPracticePointRepository.save(new TownPracticePoint());
        assertThat(townPracticePoint).isNotNull();
    }

}