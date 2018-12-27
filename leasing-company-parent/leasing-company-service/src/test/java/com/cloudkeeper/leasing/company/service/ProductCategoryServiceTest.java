package com.cloudkeeper.leasing.company.service;

import com.cloudkeeper.leasing.company.domain.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 产品分类 service 测试
 * @author asher
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryServiceTest {

    /** 产品分类 service */
    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory = productCategoryService.save(productCategory);
        assertNotNull(productCategory.getId());
    }

}