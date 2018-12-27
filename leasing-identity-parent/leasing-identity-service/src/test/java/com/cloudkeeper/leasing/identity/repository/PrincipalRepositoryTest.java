package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.Principal;
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
 * 默认用 h2 数据库，不需要配置
 * 使用注解 @AutoConfigureTestDatabase 改用手动配置的数据库
 */
@RunWith(SpringRunner.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PrincipalRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PrincipalRepository principalRepository;

    private Principal principal = null;

    private static final String TEST_CODE = "test";

    @Before
    public void before() {
        principal = entityManager.persist(new Principal().setCode(TEST_CODE));
    }

    @After
    public void after() {
        principalRepository.deleteAll();
    }

    @Test
    public void findByCode() {
        assertThat(principalRepository.findByCode(TEST_CODE)).isPresent();
        assertThat(principalRepository.findByCode(TEST_CODE + "1")).isNotPresent();
    }

    @Test
    public void existsByCodeAndIdNot() {
        boolean exist = principalRepository.existsByCodeAndIdNot(TEST_CODE, principal.getId());
        assertThat(exist).isFalse();
        exist = principalRepository.existsByCodeAndIdNot(TEST_CODE, principal.getId() + "1");
        assertThat(exist).isTrue();
    }

    @Test
    public void existsByCode() {
        boolean exist = principalRepository.existsByCode(TEST_CODE);
        assertThat(exist).isTrue();
        exist = principalRepository.existsByCode(TEST_CODE + "1");
        assertThat(exist).isFalse();
    }
}
