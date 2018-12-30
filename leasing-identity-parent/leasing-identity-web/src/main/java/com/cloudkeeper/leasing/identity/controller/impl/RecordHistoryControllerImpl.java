package com.cloudkeeper.leasing.identity.controller.impl;

import com.cloudkeeper.leasing.base.controller.impl.BaseControllerImpl;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.controller.RecordHistoryController;
import com.cloudkeeper.leasing.identity.domain.RecordHistory;
import com.cloudkeeper.leasing.identity.dto.recordhistory.RecordHistoryDTO;
import com.cloudkeeper.leasing.identity.dto.recordhistory.RecordHistorySearchable;
import com.cloudkeeper.leasing.identity.service.RecordHistoryService;
import com.cloudkeeper.leasing.identity.vo.RecordHistoryVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 活动记录历史 controller
 * @author wj
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecordHistoryControllerImpl extends BaseControllerImpl<RecordHistory, RecordHistoryDTO, RecordHistorySearchable, RecordHistoryVO> implements RecordHistoryController {

    /** 活动记录历史 service */
    private final RecordHistoryService recordHistoryService;

    @Override
    protected BaseService<RecordHistory> getBaseService() {
    return recordHistoryService;
    }

}