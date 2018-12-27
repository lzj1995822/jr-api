package com.cloudkeeper.leasing.identity.controller.impl;

import com.cloudkeeper.leasing.base.controller.impl.BaseControllerImpl;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.controller.TownPracticePointController;
import com.cloudkeeper.leasing.identity.domain.TownPracticePoint;
import com.cloudkeeper.leasing.identity.dto.townpracticepoint.TownPracticePointDTO;
import com.cloudkeeper.leasing.identity.dto.townpracticepoint.TownPracticePointSearchable;
import com.cloudkeeper.leasing.identity.service.TownPracticePointService;
import com.cloudkeeper.leasing.identity.vo.TownPracticePointVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 镇文明实践点 controller
 * @author wj
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TownPracticePointControllerImpl extends BaseControllerImpl<TownPracticePoint, TownPracticePointDTO, TownPracticePointSearchable, TownPracticePointVO> implements TownPracticePointController {

    /** 镇文明实践点 service */
    private final TownPracticePointService townPracticePointService;

    @Override
    protected BaseService<TownPracticePoint> getBaseService() {
    return townPracticePointService;
    }

}