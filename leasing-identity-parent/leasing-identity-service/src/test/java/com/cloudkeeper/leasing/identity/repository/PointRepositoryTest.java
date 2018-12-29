package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.Point;
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
 * 实践点 repository 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class PointRepositoryTest {

    /** 测试 entityManager */
    @Autowired
    private TestEntityManager entityManager;

    /** 实践点 repository */
    @Autowired
    private PointRepository pointRepository;

    @Before
    public void setUp() {
        Point point = new Point();
        point = entityManager.persist(point);
    }

    @After
    public void tearDown() {
        pointRepository.deleteAll();
    }

    @Test
    public void save() {
        Point point = pointRepository.save(new Point());
        assertThat(point).isNotNull();
    }

}