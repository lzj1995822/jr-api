package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.identity.domain.Record;
import com.cloudkeeper.leasing.identity.dto.record.RecordSearchable;

import io.swagger.annotations.ApiParam;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cloudkeeper.leasing.base.service.BaseService;

/**
 * 活动记录 service
 * @author wj
 */
public interface RecordService extends BaseService<Record> {
	
	 /**
     * 根据类型和权限分页
     * @return
     */
	Page<Record> pageByTypeAndPermission(RecordSearchable searchable, Pageable pageable);
}