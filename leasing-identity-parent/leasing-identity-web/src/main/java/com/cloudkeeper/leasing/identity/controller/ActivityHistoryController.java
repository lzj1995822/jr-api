package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.controller.BaseController;
import com.cloudkeeper.leasing.identity.dto.activityhistory.ActivityHistoryDTO;
import com.cloudkeeper.leasing.identity.dto.activityhistory.ActivityHistorySearchable;
import com.cloudkeeper.leasing.identity.vo.ActivityHistoryVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 活动记录 controller
 * @author hf
 */
@Api(value = "活动记录", tags = "活动记录")
@RequestMapping("/activityHistory")
public interface ActivityHistoryController extends BaseController<ActivityHistoryDTO, ActivityHistorySearchable, ActivityHistoryVO> {

}