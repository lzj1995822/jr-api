package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.Activity;
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
 * 活动 repository 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ActivityRepositoryTest {

    /** 测试 entityManager */
    @Autowired
    private TestEntityManager entityManager;

    /** 活动 repository */
    @Autowired
    private ActivityRepository activityRepository;

    @Before
    public void setUp() {
        Activity activity = new Activity();
        activity = entityManager.persist(activity);
    }

    @After
    public void tearDown() {
        activityRepository.deleteAll();
    }

    @Test
    public void save() {
        Activity activity = activityRepository.save(new Activity());
        assertThat(activity).isNotNull();
    }

}