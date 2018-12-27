package com.cloudkeeper.leasing.company.repository;

import com.cloudkeeper.leasing.company.domain.SecuredAssets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 担保资产 repository 测试
 * @author asher
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SecuredAssetsRepositoryTest {

    /** 担保资产 repository */
    @Autowired
    private SecuredAssetsRepository securedAssetsRepository;

    @Test
    public void saveTest() {
        SecuredAssets securedAssets = new SecuredAssets();
        securedAssets = securedAssetsRepository.save(securedAssets);
        assertNotNull(securedAssets.getId());
    }

}