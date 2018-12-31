package com.cloudkeeper.leasing.identity.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.identity.constant.ProcessConstants;
import com.cloudkeeper.leasing.identity.domain.Activity;
import com.cloudkeeper.leasing.identity.domain.Country;
import com.cloudkeeper.leasing.identity.domain.OrgCenter;
import com.cloudkeeper.leasing.identity.domain.OrgRoom;
import com.cloudkeeper.leasing.identity.domain.Principal;
import com.cloudkeeper.leasing.identity.domain.QActivity;
import com.cloudkeeper.leasing.identity.domain.QRecord;
import com.cloudkeeper.leasing.identity.domain.Record;
import com.cloudkeeper.leasing.identity.domain.RecordHistory;
import com.cloudkeeper.leasing.identity.dto.record.RecordSearchable;
import com.cloudkeeper.leasing.identity.repository.ActivityRepository;
import com.cloudkeeper.leasing.identity.repository.RecordRepository;
import com.cloudkeeper.leasing.identity.service.CountryService;
import com.cloudkeeper.leasing.identity.service.OrgCenterService;
import com.cloudkeeper.leasing.identity.service.OrgRoomService;
import com.cloudkeeper.leasing.identity.service.PrincipalService;
import com.cloudkeeper.leasing.identity.service.RecordHistoryService;
import com.cloudkeeper.leasing.identity.service.RecordService;
import com.querydsl.core.BooleanBuilder;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 活动记录 service
 * @author wj
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecordServiceImpl extends BaseServiceImpl<Record> implements RecordService {

    /** 活动记录 repository */
    private final RecordRepository recordRepository;
    
    /** 活动 repository */
    private final ActivityRepository activityRepository;
    
    private final OrgRoomService orgRoomService;

    private final OrgCenterService orgCenterService;

    private final CountryService countryService;

    private final PrincipalService principalService;
    
    private final RecordHistoryService recordHistoryService;

    @Override
    protected BaseRepository<Record> getBaseRepository() {
        return recordRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("status", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("content", ExampleMatcher.GenericPropertyMatchers.contains());
    }

    @Nonnull
	@Override
	public Record save(Record entity) {
		RecordHistory recordHistory = new RecordHistory();
		BeanUtils.copyProperties(entity, recordHistory, "id");
		entity = super.save(entity);
		recordHistory.setRecordId(entity.getId());
		recordHistoryService.save(recordHistory);
		return entity;
	}

	@Override
	public Page<Record> pageByTypeAndPermission(RecordSearchable searchable, Pageable pageable) {
		Principal principal = (Principal)principalService.getCurrentPrincipal();
		QRecord qRecord = QRecord.record;
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		String orgId = principal.getOrgId();
		if (principal.getType().equals(ProcessConstants.ORG_CENTER)){
            OrgCenter orgCenter = orgCenterService.findById(orgId);
            booleanBuilder.and(qRecord.activity.activityType.eq(orgCenter.getType()));
            booleanBuilder.and(qRecord.status.eq(ProcessConstants.RECORD_CITY_PASSED));    
		}else if (principal.getType().equals( ProcessConstants.ORG_ROOM)) {
            OrgRoom orgRoom = orgRoomService.findById(orgId);
            List<Country> allByTownId = countryService.findAllByTownId(orgRoom.getTownId());
            booleanBuilder.and(qRecord.activity.activityType.eq(orgRoom.getType()))
                    .and(qRecord.activity.creator.type.eq(ProcessConstants.ORG_CENTER)
                            .or(qRecord.activity.creator.id.in(allByTownId.stream().map(Country::getId).collect(Collectors.toList()))));    
		}else if  (principal.getType().equals( ProcessConstants.ORG_COUNTRY)){
			booleanBuilder.and(qRecord.status.eq(ProcessConstants.RECORD_CITY_PASSED));
            booleanBuilder.and(qRecord.createdBy.eq(principal.getId()));
            booleanBuilder.or(qRecord.activity.type.eq(ProcessConstants.ACT_TYPE_CENTER));
		}
		return super.findAll(booleanBuilder, pageable);
	}
      
}