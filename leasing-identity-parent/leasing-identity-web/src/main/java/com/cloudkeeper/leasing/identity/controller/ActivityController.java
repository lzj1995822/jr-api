package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.controller.BaseController;
import com.cloudkeeper.leasing.identity.dto.activity.ActivityDTO;
import com.cloudkeeper.leasing.identity.dto.activity.ActivitySearchable;
import com.cloudkeeper.leasing.identity.vo.ActivityVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 活动 controller
 * @author wj
 */
@Api(value = "活动", tags = "活动")
@RequestMapping("/activity")
public interface ActivityController extends BaseController<ActivityDTO, ActivitySearchable, ActivityVO> {

}