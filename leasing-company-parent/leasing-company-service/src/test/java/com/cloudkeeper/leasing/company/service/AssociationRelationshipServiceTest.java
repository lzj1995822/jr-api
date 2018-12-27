package com.cloudkeeper.leasing.company.service;

import com.cloudkeeper.leasing.company.domain.AssociationRelationship;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 关联关系 service 测试
 * @author asher
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AssociationRelationshipServiceTest {

    /** 关联关系 service */
    @Autowired
    private AssociationRelationshipService associationRelationshipService;

    @Test
    public void saveTest() {
        AssociationRelationship associationRelationship = new AssociationRelationship();
        associationRelationship = associationRelationshipService.save(associationRelationship);
        assertNotNull(associationRelationship.getId());
    }

}