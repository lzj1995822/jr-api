package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.CodeConfig;
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
 * 编码配置 repository 测试
 * @author asher
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CodeConfigRepositoryTest {

    /** 测试 entityManager */
    @Autowired
    private TestEntityManager entityManager;

    /** 编码配置 repository */
    @Autowired
    private CodeConfigRepository codeConfigRepository;

    @Before
    public void setUp() {
        CodeConfig codeConfig = new CodeConfig();
        codeConfig = entityManager.persist(codeConfig);
    }

    @After
    public void tearDown() {
        codeConfigRepository.deleteAll();
    }

    @Test
    public void save() {
        CodeConfig codeConfig = codeConfigRepository.save(new CodeConfig());
        assertThat(codeConfig).isNotNull();
    }

}