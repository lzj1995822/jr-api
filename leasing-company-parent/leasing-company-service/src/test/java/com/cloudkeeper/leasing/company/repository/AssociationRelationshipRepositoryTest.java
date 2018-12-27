package com.cloudkeeper.leasing.company.repository;

import com.cloudkeeper.leasing.company.domain.AssociationRelationship;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 关联关系 repository 测试
 * @author asher
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AssociationRelationshipRepositoryTest {

    /** 关联关系 repository */
    @Autowired
    private AssociationRelationshipRepository associationRelationshipRepository;

    @Test
    public void saveTest() {
        AssociationRelationship associationRelationship = new AssociationRelationship();
        associationRelationship = associationRelationshipRepository.save(associationRelationship);
        assertNotNull(associationRelationship.getId());
    }

}