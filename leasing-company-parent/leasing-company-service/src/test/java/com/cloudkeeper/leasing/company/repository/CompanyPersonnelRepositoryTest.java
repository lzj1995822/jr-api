package com.cloudkeeper.leasing.company.repository;

import com.cloudkeeper.leasing.company.domain.CompanyPersonnel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 公司相关人员 repository 测试
 * @author asher
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompanyPersonnelRepositoryTest {

    /** 公司相关人员 repository */
    @Autowired
    private CompanyPersonnelRepository companyPersonnelRepository;

    @Test
    public void saveTest() {
        CompanyPersonnel companyPersonnel = new CompanyPersonnel();
        companyPersonnel = companyPersonnelRepository.save(companyPersonnel);
        assertNotNull(companyPersonnel.getId());
    }

}