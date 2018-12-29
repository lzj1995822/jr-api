package com.cloudkeeper.leasing.identity.controller.impl;

import com.cloudkeeper.leasing.base.controller.impl.BaseControllerImpl;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.controller.OrgCenterController;
import com.cloudkeeper.leasing.identity.domain.OrgCenter;
import com.cloudkeeper.leasing.identity.dto.orgcenter.OrgCenterDTO;
import com.cloudkeeper.leasing.identity.dto.orgcenter.OrgCenterSearchable;
import com.cloudkeeper.leasing.identity.service.OrgCenterService;
import com.cloudkeeper.leasing.identity.vo.OrgCenterVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 分中心 controller
 * @author wj
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrgCenterControllerImpl extends BaseControllerImpl<OrgCenter, OrgCenterDTO, OrgCenterSearchable, OrgCenterVO> implements OrgCenterController {

    /** 分中心 service */
    private final OrgCenterService orgCenterService;

    @Override
    protected BaseService<OrgCenter> getBaseService() {
    return orgCenterService;
    }

}