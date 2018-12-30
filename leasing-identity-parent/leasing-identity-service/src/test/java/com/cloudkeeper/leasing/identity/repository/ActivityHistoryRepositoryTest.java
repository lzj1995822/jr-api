package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.ActivityHistory;
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
 * 活动记录 repository 测试
 * @author hf
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ActivityHistoryRepositoryTest {

    /** 测试 entityManager */
    @Autowired
    private TestEntityManager entityManager;

    /** 活动记录 repository */
    @Autowired
    private ActivityHistoryRepository activityHistoryRepository;

    @Before
    public void setUp() {
        ActivityHistory activityHistory = new ActivityHistory();
        activityHistory = entityManager.persist(activityHistory);
    }

    @After
    public void tearDown() {
        activityHistoryRepository.deleteAll();
    }

    @Test
    public void save() {
        ActivityHistory activityHistory = activityHistoryRepository.save(new ActivityHistory());
        assertThat(activityHistory).isNotNull();
    }

}