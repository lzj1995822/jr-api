package com.cloudkeeper.leasing.identity.controller.impl;

import com.cloudkeeper.leasing.base.controller.impl.BaseControllerImpl;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.controller.ActivityHistoryController;
import com.cloudkeeper.leasing.identity.domain.ActivityHistory;
import com.cloudkeeper.leasing.identity.dto.activityhistory.ActivityHistoryDTO;
import com.cloudkeeper.leasing.identity.dto.activityhistory.ActivityHistorySearchable;
import com.cloudkeeper.leasing.identity.service.ActivityHistoryService;
import com.cloudkeeper.leasing.identity.vo.ActivityHistoryVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 活动记录 controller
 * @author hf
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ActivityHistoryControllerImpl extends BaseControllerImpl<ActivityHistory, ActivityHistoryDTO, ActivityHistorySearchable, ActivityHistoryVO> implements ActivityHistoryController {

    /** 活动记录 service */
    private final ActivityHistoryService activityHistoryService;

    @Override
    protected BaseService<ActivityHistory> getBaseService() {
    return activityHistoryService;
    }

}