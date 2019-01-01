package com.cloudkeeper.leasing.identity.controller.impl;

import com.cloudkeeper.leasing.base.controller.impl.BaseControllerImpl;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.controller.TownActivityController;
import com.cloudkeeper.leasing.identity.domain.TownActivity;
import com.cloudkeeper.leasing.identity.dto.townactivity.TownActivityDTO;
import com.cloudkeeper.leasing.identity.dto.townactivity.TownActivitySearchable;
import com.cloudkeeper.leasing.identity.service.TownActivityService;
import com.cloudkeeper.leasing.identity.vo.TownActivityVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 镇活动 controller
 * @author hf
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TownActivityControllerImpl extends BaseControllerImpl<TownActivity, TownActivityDTO, TownActivitySearchable, TownActivityVO> implements TownActivityController {

    /** 镇活动 service */
    private final TownActivityService townActivityService;

    @Override
    protected BaseService<TownActivity> getBaseService() {
    return townActivityService;
    }

}