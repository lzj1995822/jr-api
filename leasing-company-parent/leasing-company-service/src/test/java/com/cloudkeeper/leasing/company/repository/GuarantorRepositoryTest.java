package com.cloudkeeper.leasing.company.repository;

import com.cloudkeeper.leasing.company.domain.Guarantor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 担保人 repository 测试
 * @author asher
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GuarantorRepositoryTest {

    /** 担保人 repository */
    @Autowired
    private GuarantorRepository guarantorRepository;

    @Test
    public void saveTest() {
        Guarantor guarantor = new Guarantor();
        guarantor = guarantorRepository.save(guarantor);
        assertNotNull(guarantor.getId());
    }

}