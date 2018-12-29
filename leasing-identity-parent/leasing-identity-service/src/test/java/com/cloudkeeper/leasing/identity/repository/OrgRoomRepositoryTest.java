package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.OrgRoom;
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
 * 功能室 repository 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class OrgRoomRepositoryTest {

    /** 测试 entityManager */
    @Autowired
    private TestEntityManager entityManager;

    /** 功能室 repository */
    @Autowired
    private OrgRoomRepository orgRoomRepository;

    @Before
    public void setUp() {
        OrgRoom orgRoom = new OrgRoom();
        orgRoom = entityManager.persist(orgRoom);
    }

    @After
    public void tearDown() {
        orgRoomRepository.deleteAll();
    }

    @Test
    public void save() {
        OrgRoom orgRoom = orgRoomRepository.save(new OrgRoom());
        assertThat(orgRoom).isNotNull();
    }

}