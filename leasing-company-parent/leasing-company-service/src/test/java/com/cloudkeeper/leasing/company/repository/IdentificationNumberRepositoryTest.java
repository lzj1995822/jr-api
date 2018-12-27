package com.cloudkeeper.leasing.company.repository;

import com.cloudkeeper.leasing.company.domain.IdentificationNumber;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 机器编号 repository 测试
 * @author asher
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class IdentificationNumberRepositoryTest {

    /** 机器编号 repository */
    @Autowired
    private IdentificationNumberRepository identificationNumberRepository;

    @Test
    public void saveTest() {
        IdentificationNumber identificationNumber = new IdentificationNumber();
        identificationNumber = identificationNumberRepository.save(identificationNumber);
        assertNotNull(identificationNumber.getId());
    }

}