package com.cloudkeeper.leasing.identity.controller.impl;

import com.cloudkeeper.leasing.base.controller.impl.BaseControllerImpl;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.controller.ActivityController;
import com.cloudkeeper.leasing.identity.domain.Activity;
import com.cloudkeeper.leasing.identity.dto.activity.ActivityDTO;
import com.cloudkeeper.leasing.identity.dto.activity.ActivitySearchable;
import com.cloudkeeper.leasing.identity.service.ActivityService;
import com.cloudkeeper.leasing.identity.vo.ActivityVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 活动 controller
 * @author wj
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ActivityControllerImpl extends BaseControllerImpl<Activity, ActivityDTO, ActivitySearchable, ActivityVO> implements ActivityController {

    /** 活动 service */
    private final ActivityService activityService;

    @Override
    protected BaseService<Activity> getBaseService() {
    return activityService;
    }

    @Override
    public Result<Page<ActivityVO>> page(ActivitySearchable searchable, Pageable pageable) {
        Page<Activity> activities = activityService.pageByTypeAndPermission(pageable, searchable);
        Page<ActivityVO> convert = Activity.convert(activities, ActivityVO.class);
        return Result.of(convert);
    }
}