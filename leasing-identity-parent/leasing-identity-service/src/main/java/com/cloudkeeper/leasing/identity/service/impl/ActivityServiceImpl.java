package com.cloudkeeper.leasing.identity.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.identity.constant.ProcessConstants;
import com.cloudkeeper.leasing.identity.domain.*;
import com.cloudkeeper.leasing.identity.dto.activity.ActivitySearchable;
import com.cloudkeeper.leasing.identity.repository.ActivityRepository;
import com.cloudkeeper.leasing.identity.service.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * 活动 service
 * @author wj
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ActivityServiceImpl extends BaseServiceImpl<Activity> implements ActivityService {

    /** 活动 repository */
    private final ActivityRepository activityRepository;

    private final OrgRoomService orgRoomService;

    private final OrgCenterService orgCenterService;

    private final CountryService countryService;

    private final PrincipalService principalService;

    @Override
    protected BaseRepository<Activity> getBaseRepository() {
        return activityRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("des", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("url", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("type", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("status", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("isSpecial", ExampleMatcher.GenericPropertyMatchers.contains());
    }

    @Override
    public Page<Activity> pageByTypeAndPermission(Pageable pageable, ActivitySearchable activitySearchable) {
        Principal principal = (Principal)((Optional) principalService.getCurrentPrincipal()).get();
        QActivity qActivity = QActivity.activity;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        String orgId = principal.getOrgId();
        if (principal.getType().equals(ProcessConstants.ORG_CENTER)){
            OrgCenter orgCenter = orgCenterService.findById(orgId);
            booleanBuilder.and(qActivity.activityType.eq(orgCenter.getType()));
            booleanBuilder.and(qActivity.status.eq(ProcessConstants.ACTIVITY_CITY_PASSED));
        } else if (principal.getType().equals( ProcessConstants.ORG_ROOM)) {
//            countryService.findAllb

            OrgRoom orgRoom = orgRoomService.findById(orgId);
            booleanBuilder.and(qActivity.activityType.eq(orgRoom.getType()))
                    .and(qActivity.creator.type.eq(ProcessConstants.ORG_CENTER));
//                            .or(qActivity.creator.id.in()))
        } else if  (principal.getType().equals( ProcessConstants.ORG_COUNTRY)){
            Country country = countryService.findById(orgId);
            booleanBuilder.and(qActivity.status.eq(ProcessConstants.ACTIVITY_CITY_PASSED));
            booleanBuilder.and(qActivity.createdBy.eq(principal.getId()));
            booleanBuilder.or(qActivity.type.eq(ProcessConstants.ACT_TYPE_CENTER));
        }
        return super.findAll(booleanBuilder, pageable);
    }
}