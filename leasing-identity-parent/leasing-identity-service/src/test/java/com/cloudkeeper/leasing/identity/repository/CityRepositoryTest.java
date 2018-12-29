package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.City;
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
 * 市 repository 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CityRepositoryTest {

    /** 测试 entityManager */
    @Autowired
    private TestEntityManager entityManager;

    /** 市 repository */
    @Autowired
    private CityRepository cityRepository;

    @Before
    public void setUp() {
        City city = new City();
        city = entityManager.persist(city);
    }

    @After
    public void tearDown() {
        cityRepository.deleteAll();
    }

    @Test
    public void save() {
        City city = cityRepository.save(new City());
        assertThat(city).isNotNull();
    }

}