package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.JrResource;
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
 * 文件上传 repository 测试
 * @author hf
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class JrResourceRepositoryTest {

    /** 测试 entityManager */
    @Autowired
    private TestEntityManager entityManager;

    /** 文件上传 repository */
    @Autowired
    private JrResourceRepository jrResourceRepository;

    @Before
    public void setUp() {
        JrResource jrResource = new JrResource();
        jrResource = entityManager.persist(jrResource);
    }

    @After
    public void tearDown() {
        jrResourceRepository.deleteAll();
    }

    @Test
    public void save() {
        JrResource jrResource = jrResourceRepository.save(new JrResource());
        assertThat(jrResource).isNotNull();
    }

}