package com.cloudkeeper.leasing.company.repository;

import com.cloudkeeper.leasing.company.domain.CustomerDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 客户详细、开票资料 repository 测试
 * @author asher
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerDetailRepositoryTest {

    /** 客户详细、开票资料 repository */
    @Autowired
    private CustomerDetailRepository customerDetailRepository;

    @Test
    public void saveTest() {
        CustomerDetail customerDetail = new CustomerDetail();
        customerDetail = customerDetailRepository.save(customerDetail);
        assertNotNull(customerDetail.getId());
    }

}