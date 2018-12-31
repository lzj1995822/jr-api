package com.cloudkeeper.leasing.identity.controller.impl;

import com.cloudkeeper.leasing.base.controller.impl.BaseControllerImpl;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.controller.RecordController;
import com.cloudkeeper.leasing.identity.domain.Record;
import com.cloudkeeper.leasing.identity.dto.record.RecordDTO;
import com.cloudkeeper.leasing.identity.dto.record.RecordSearchable;
import com.cloudkeeper.leasing.identity.service.RecordService;
import com.cloudkeeper.leasing.identity.vo.RecordVO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 活动记录 controller
 * @author wj
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecordControllerImpl extends BaseControllerImpl<Record, RecordDTO, RecordSearchable, RecordVO> implements RecordController {

    /** 活动记录 service */
    private final RecordService recordService;

    @Override
    protected BaseService<Record> getBaseService() {
    return recordService;
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
	@Override
	public Result<Page<RecordVO>> page(@ApiParam(value = "查询条件", required = true) RecordSearchable searchable, 
			@ApiParam(value = "分页条件", required = true) Pageable pageable) {
    	Page<Record> records = recordService.pageByTypeAndPermission(searchable, pageable);
    	Page<RecordVO> convert = Record.convert(records, RecordVO.class);
		return Result.of(convert);
	}


}