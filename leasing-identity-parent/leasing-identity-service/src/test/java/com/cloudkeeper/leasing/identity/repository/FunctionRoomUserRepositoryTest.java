package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.FunctionRoomUser;
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
 * 功能室人员 repository 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class FunctionRoomUserRepositoryTest {

    /** 测试 entityManager */
    @Autowired
    private TestEntityManager entityManager;

    /** 功能室人员 repository */
    @Autowired
    private FunctionRoomUserRepository functionRoomUserRepository;

    @Before
    public void setUp() {
        FunctionRoomUser functionRoomUser = new FunctionRoomUser();
        functionRoomUser = entityManager.persist(functionRoomUser);
    }

    @After
    public void tearDown() {
        functionRoomUserRepository.deleteAll();
    }

    @Test
    public void save() {
        FunctionRoomUser functionRoomUser = functionRoomUserRepository.save(new FunctionRoomUser());
        assertThat(functionRoomUser).isNotNull();
    }

}