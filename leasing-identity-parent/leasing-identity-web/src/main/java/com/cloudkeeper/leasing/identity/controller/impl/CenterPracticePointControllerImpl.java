package com.cloudkeeper.leasing.identity.controller.impl;

import com.cloudkeeper.leasing.base.controller.impl.BaseControllerImpl;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.controller.CenterPracticePointController;
import com.cloudkeeper.leasing.identity.domain.CenterPracticePoint;
import com.cloudkeeper.leasing.identity.dto.centerpracticepoint.CenterPracticePointDTO;
import com.cloudkeeper.leasing.identity.dto.centerpracticepoint.CenterPracticePointSearchable;
import com.cloudkeeper.leasing.identity.service.CenterPracticePointService;
import com.cloudkeeper.leasing.identity.vo.CenterPracticePointVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 分中心文明实践点 controller
 * @author wj
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CenterPracticePointControllerImpl extends BaseControllerImpl<CenterPracticePoint, CenterPracticePointDTO, CenterPracticePointSearchable, CenterPracticePointVO> implements CenterPracticePointController {

    /** 分中心文明实践点 service */
    private final CenterPracticePointService centerPracticePointService;

    @Override
    protected BaseService<CenterPracticePoint> getBaseService() {
    return centerPracticePointService;
    }

}