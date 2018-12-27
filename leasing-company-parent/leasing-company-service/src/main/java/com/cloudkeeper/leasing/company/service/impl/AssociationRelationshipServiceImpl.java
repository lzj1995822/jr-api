package com.cloudkeeper.leasing.company.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.company.domain.AssociationRelationship;
import com.cloudkeeper.leasing.company.domain.QAssociationRelationship;
import com.cloudkeeper.leasing.company.dto.associationrelationship.AssociationRelationshipSearchable;
import com.cloudkeeper.leasing.company.repository.AssociationRelationshipRepository;
import com.cloudkeeper.leasing.company.service.AssociationRelationshipService;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 关联关系 service
 * @author asher
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AssociationRelationshipServiceImpl extends BaseServiceImpl<AssociationRelationship> implements AssociationRelationshipService {

    /** 关联关系 repository */
    private final AssociationRelationshipRepository associationRelationshipRepository;

    @Override
    protected BaseRepository<AssociationRelationship> getBaseRepository() {
        return associationRelationshipRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("customerId", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("associateId", ExampleMatcher.GenericPropertyMatchers.contains());
    }

    @Override
    public BooleanBuilder getBooleanBuilder(AssociationRelationshipSearchable associationRelationshipSearchable) {
        QAssociationRelationship qAssociationRelationship = QAssociationRelationship.associationRelationship;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (StringUtils.hasText(associationRelationshipSearchable.getCustomerName())) {
            booleanBuilder.and(qAssociationRelationship.customer.commonCompany.name.contains(associationRelationshipSearchable.getCustomerName()));
        }
        if (StringUtils.hasText(associationRelationshipSearchable.getAssociateName())) {
            booleanBuilder.and(qAssociationRelationship.associate.commonCompany.name.contains(associationRelationshipSearchable.getAssociateName()));
        }
        return booleanBuilder;
    }



}