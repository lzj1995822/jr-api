package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.CodeRecord;
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
 * 编码生成记录 repository 测试
 * @author asher
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CodeRecordRepositoryTest {

    /** 测试 entityManager */
    @Autowired
    private TestEntityManager entityManager;

    /** 编码生成记录 repository */
    @Autowired
    private CodeRecordRepository codeRecordRepository;

    @Before
    public void setUp() {
        CodeRecord codeRecord = new CodeRecord();
        codeRecord = entityManager.persist(codeRecord);
    }

    @After
    public void tearDown() {
        codeRecordRepository.deleteAll();
    }

    @Test
    public void save() {
        CodeRecord codeRecord = codeRecordRepository.save(new CodeRecord());
        assertThat(codeRecord).isNotNull();
    }

}