package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.Record;
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
 * @author wj
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class RecordRepositoryTest {

    /** 测试 entityManager */
    @Autowired
    private TestEntityManager entityManager;

    /** 活动记录 repository */
    @Autowired
    private RecordRepository recordRepository;

    @Before
    public void setUp() {
        Record record = new Record();
        record = entityManager.persist(record);
    }

    @After
    public void tearDown() {
        recordRepository.deleteAll();
    }

    @Test
    public void save() {
        Record record = recordRepository.save(new Record());
        assertThat(record).isNotNull();
    }

}