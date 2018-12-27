package com.cloudkeeper.leasing.company.service;

import com.cloudkeeper.leasing.company.domain.AssociationRelationship;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.company.dto.associationrelationship.AssociationRelationshipSearchable;
import com.querydsl.core.BooleanBuilder;

/**
 * 关联关系 service
 * @author asher
 */
public interface AssociationRelationshipService extends BaseService<AssociationRelationship> {

    /**
     * 获取BooleanBuilder查询对象
     * @param associationRelationshipSearchable 关联关系可查询对象
     * @return BooleanBuilder查询对象
     */
    BooleanBuilder getBooleanBuilder(AssociationRelationshipSearchable associationRelationshipSearchable);
}