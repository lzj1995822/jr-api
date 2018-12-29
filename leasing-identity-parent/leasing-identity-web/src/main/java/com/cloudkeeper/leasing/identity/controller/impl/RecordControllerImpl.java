package com.cloudkeeper.leasing.identity.controller.impl;

import com.cloudkeeper.leasing.base.controller.impl.BaseControllerImpl;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.controller.RecordController;
import com.cloudkeeper.leasing.identity.domain.Record;
import com.cloudkeeper.leasing.identity.dto.record.RecordDTO;
import com.cloudkeeper.leasing.identity.dto.record.RecordSearchable;
import com.cloudkeeper.leasing.identity.service.RecordService;
import com.cloudkeeper.leasing.identity.vo.RecordVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

}