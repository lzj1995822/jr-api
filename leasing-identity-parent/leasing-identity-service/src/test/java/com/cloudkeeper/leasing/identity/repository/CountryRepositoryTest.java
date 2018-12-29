package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.Country;
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
 * 村 repository 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CountryRepositoryTest {

    /** 测试 entityManager */
    @Autowired
    private TestEntityManager entityManager;

    /** 村 repository */
    @Autowired
    private CountryRepository countryRepository;

    @Before
    public void setUp() {
        Country country = new Country();
        country = entityManager.persist(country);
    }

    @After
    public void tearDown() {
        countryRepository.deleteAll();
    }

    @Test
    public void save() {
        Country country = countryRepository.save(new Country());
        assertThat(country).isNotNull();
    }

}