package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.CenterPracticePoint;
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
 * 分中心文明实践点 repository 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CenterPracticePointRepositoryTest {

    /** 测试 entityManager */
    @Autowired
    private TestEntityManager entityManager;

    /** 分中心文明实践点 repository */
    @Autowired
    private CenterPracticePointRepository centerPracticePointRepository;

    @Before
    public void setUp() {
        CenterPracticePoint centerPracticePoint = new CenterPracticePoint();
        centerPracticePoint = entityManager.persist(centerPracticePoint);
    }

    @After
    public void tearDown() {
        centerPracticePointRepository.deleteAll();
    }

    @Test
    public void save() {
        CenterPracticePoint centerPracticePoint = centerPracticePointRepository.save(new CenterPracticePoint());
        assertThat(centerPracticePoint).isNotNull();
    }

}