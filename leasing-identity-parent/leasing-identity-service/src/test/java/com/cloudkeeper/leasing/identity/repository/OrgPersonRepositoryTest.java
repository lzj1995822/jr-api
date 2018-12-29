package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.OrgPerson;
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
 * 组织架构人员 repository 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class OrgPersonRepositoryTest {

    /** 测试 entityManager */
    @Autowired
    private TestEntityManager entityManager;

    /** 组织架构人员 repository */
    @Autowired
    private OrgPersonRepository orgPersonRepository;

    @Before
    public void setUp() {
        OrgPerson orgPerson = new OrgPerson();
        orgPerson = entityManager.persist(orgPerson);
    }

    @After
    public void tearDown() {
        orgPersonRepository.deleteAll();
    }

    @Test
    public void save() {
        OrgPerson orgPerson = orgPersonRepository.save(new OrgPerson());
        assertThat(orgPerson).isNotNull();
    }

}