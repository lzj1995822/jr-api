package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.RecordHistory;
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
 * 活动记录历史 repository 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class RecordHistoryRepositoryTest {

    /** 测试 entityManager */
    @Autowired
    private TestEntityManager entityManager;

    /** 活动记录历史 repository */
    @Autowired
    private RecordHistoryRepository recordHistoryRepository;

    @Before
    public void setUp() {
        RecordHistory recordHistory = new RecordHistory();
        recordHistory = entityManager.persist(recordHistory);
    }

    @After
    public void tearDown() {
        recordHistoryRepository.deleteAll();
    }

    @Test
    public void save() {
        RecordHistory recordHistory = recordHistoryRepository.save(new RecordHistory());
        assertThat(recordHistory).isNotNull();
    }

}