package com.cloudkeeper.leasing.company.service;

import com.cloudkeeper.leasing.company.domain.ProductModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 产品型号 service 测试
 * @author asher
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductModelServiceTest {

    /** 产品型号 service */
    @Autowired
    private ProductModelService productModelService;

    @Test
    public void saveTest() {
        ProductModel productModel = new ProductModel();
        productModel = productModelService.save(productModel);
        assertNotNull(productModel.getId());
    }

}