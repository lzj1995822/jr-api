package com.cloudkeeper.leasing.identity.controller.impl;

import com.cloudkeeper.leasing.base.controller.impl.BaseControllerImpl;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.controller.CenterController;
import com.cloudkeeper.leasing.identity.domain.Center;
import com.cloudkeeper.leasing.identity.dto.center.CenterDTO;
import com.cloudkeeper.leasing.identity.dto.center.CenterSearchable;
import com.cloudkeeper.leasing.identity.service.CenterService;
import com.cloudkeeper.leasing.identity.vo.CenterVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 分中心 controller
 * @author wj
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CenterControllerImpl extends BaseControllerImpl<Center, CenterDTO, CenterSearchable, CenterVO> implements CenterController {

    /** 分中心 service */
    private final CenterService centerService;

    @Override
    protected BaseService<Center> getBaseService() {
    return centerService;
    }

}