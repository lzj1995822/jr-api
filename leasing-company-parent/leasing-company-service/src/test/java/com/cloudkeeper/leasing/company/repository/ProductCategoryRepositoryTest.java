package com.cloudkeeper.leasing.company.repository;

import com.cloudkeeper.leasing.company.domain.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 产品分类 repository 测试
 * @author asher
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductCategoryRepositoryTest {

    /** 产品分类 repository */
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory = productCategoryRepository.save(productCategory);
        assertNotNull(productCategory.getId());
    }

}