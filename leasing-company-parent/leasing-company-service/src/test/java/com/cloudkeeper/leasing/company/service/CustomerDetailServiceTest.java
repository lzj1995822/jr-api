package com.cloudkeeper.leasing.company.service;

import com.cloudkeeper.leasing.company.domain.CustomerDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 客户详细、开票资料 service 测试
 * @author asher
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CustomerDetailServiceTest {

    /** 客户详细、开票资料 service */
    @Autowired
    private CustomerDetailService customerDetailService;

    @Test
    public void saveTest() {
        CustomerDetail customerDetail = new CustomerDetail();
        customerDetail = customerDetailService.save(customerDetail);
        assertNotNull(customerDetail.getId());
    }

}