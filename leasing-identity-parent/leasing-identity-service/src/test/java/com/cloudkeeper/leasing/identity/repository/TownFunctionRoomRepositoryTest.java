package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.TownFunctionRoom;
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
 * 所站功能室建设 repository 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class TownFunctionRoomRepositoryTest {

    /** 测试 entityManager */
    @Autowired
    private TestEntityManager entityManager;

    /** 所站功能室建设 repository */
    @Autowired
    private TownFunctionRoomRepository townFunctionRoomRepository;

    @Before
    public void setUp() {
        TownFunctionRoom townFunctionRoom = new TownFunctionRoom();
        townFunctionRoom = entityManager.persist(townFunctionRoom);
    }

    @After
    public void tearDown() {
        townFunctionRoomRepository.deleteAll();
    }

    @Test
    public void save() {
        TownFunctionRoom townFunctionRoom = townFunctionRoomRepository.save(new TownFunctionRoom());
        assertThat(townFunctionRoom).isNotNull();
    }

}