package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.RecordSpecial;
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
 * 特殊活动 repository 测试
 * @author hf
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class RecordSpecialRepositoryTest {

    /** 测试 entityManager */
    @Autowired
    private TestEntityManager entityManager;

    /** 特殊活动 repository */
    @Autowired
    private RecordSpecialRepository recordSpecialRepository;

    @Before
    public void setUp() {
        RecordSpecial recordSpecial = new RecordSpecial();
        recordSpecial = entityManager.persist(recordSpecial);
    }

    @After
    public void tearDown() {
        recordSpecialRepository.deleteAll();
    }

    @Test
    public void save() {
        RecordSpecial recordSpecial = recordSpecialRepository.save(new RecordSpecial());
        assertThat(recordSpecial).isNotNull();
    }

}