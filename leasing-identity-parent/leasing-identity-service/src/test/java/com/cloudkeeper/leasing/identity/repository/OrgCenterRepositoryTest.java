package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.OrgCenter;
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
 * 分中心 repository 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class OrgCenterRepositoryTest {

    /** 测试 entityManager */
    @Autowired
    private TestEntityManager entityManager;

    /** 分中心 repository */
    @Autowired
    private OrgCenterRepository orgCenterRepository;

    @Before
    public void setUp() {
        OrgCenter orgCenter = new OrgCenter();
        orgCenter = entityManager.persist(orgCenter);
    }

    @After
    public void tearDown() {
        orgCenterRepository.deleteAll();
    }

    @Test
    public void save() {
        OrgCenter orgCenter = orgCenterRepository.save(new OrgCenter());
        assertThat(orgCenter).isNotNull();
    }

}