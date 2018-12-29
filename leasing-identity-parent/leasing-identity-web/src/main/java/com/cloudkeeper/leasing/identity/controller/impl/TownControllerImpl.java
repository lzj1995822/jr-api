package com.cloudkeeper.leasing.identity.controller.impl;

import com.cloudkeeper.leasing.base.controller.impl.BaseControllerImpl;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.controller.TownController;
import com.cloudkeeper.leasing.identity.domain.Town;
import com.cloudkeeper.leasing.identity.dto.town.TownDTO;
import com.cloudkeeper.leasing.identity.dto.town.TownSearchable;
import com.cloudkeeper.leasing.identity.service.TownService;
import com.cloudkeeper.leasing.identity.vo.TownVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 镇 controller
 * @author wj
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TownControllerImpl extends BaseControllerImpl<Town, TownDTO, TownSearchable, TownVO> implements TownController {

    /** 镇 service */
    private final TownService townService;

    @Override
    protected BaseService<Town> getBaseService() {
    return townService;
    }

}