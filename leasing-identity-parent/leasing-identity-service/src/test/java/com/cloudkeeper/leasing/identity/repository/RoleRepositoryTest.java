package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.Role;
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
 * 角色 repository 测试
 * @author jerry
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class RoleRepositoryTest {

    /** 测试 entityManager */
    @Autowired
    private TestEntityManager entityManager;

    /** 角色 repository */
    @Autowired
    private RoleRepository roleRepository;

    private static final String CODE_A = "a";
    private static final String CODE_B = "b";
    private static final String CODE_C = "c";
    private Role roleA;

    @Before
    public void setUp() {
        roleA = entityManager.persist(new Role().setCode(CODE_A));
        entityManager.persist(new Role().setCode(CODE_B));
    }

    @After
    public void tearDown() {
        roleRepository.deleteAll();
    }

    @Test
    public void save() {
        Role role = roleRepository.save(new Role());
        assertThat(role).isNotNull();
    }

    @Test
    public void existsByCodeAndIdNot() {
        boolean exists = roleRepository.existsByCodeAndIdNot(CODE_A, roleA.getId());
        assertThat(exists).isFalse();
        exists = roleRepository.existsByCodeAndIdNot(CODE_B, roleA.getId());
        assertThat(exists).isTrue();
    }

    @Test
    public void existsByCode() {
        boolean exists = roleRepository.existsByCode(CODE_A);
        assertThat(exists).isTrue();
        exists = roleRepository.existsByCode(CODE_C);
        assertThat(exists).isFalse();
    }
}