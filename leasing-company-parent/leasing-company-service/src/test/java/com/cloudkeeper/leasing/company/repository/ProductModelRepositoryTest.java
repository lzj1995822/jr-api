package com.cloudkeeper.leasing.company.repository;

import com.cloudkeeper.leasing.company.domain.ProductModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 产品型号 repository 测试
 * @author asher
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductModelRepositoryTest {

    /** 产品型号 repository */
    @Autowired
    private ProductModelRepository productModelRepository;

    @Test
    public void saveTest() {
        ProductModel productModel = new ProductModel();
        productModel = productModelRepository.save(productModel);
        assertNotNull(productModel.getId());
    }

}