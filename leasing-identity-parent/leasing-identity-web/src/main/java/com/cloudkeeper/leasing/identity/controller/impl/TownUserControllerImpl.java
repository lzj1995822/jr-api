package com.cloudkeeper.leasing.identity.controller.impl;

import com.cloudkeeper.leasing.base.controller.impl.BaseControllerImpl;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.controller.TownUserController;
import com.cloudkeeper.leasing.identity.domain.TownUser;
import com.cloudkeeper.leasing.identity.dto.townuser.TownUserDTO;
import com.cloudkeeper.leasing.identity.dto.townuser.TownUserSearchable;
import com.cloudkeeper.leasing.identity.service.TownUserService;
import com.cloudkeeper.leasing.identity.vo.TownUserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 所站人员 controller
 * @author wj
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TownUserControllerImpl extends BaseControllerImpl<TownUser, TownUserDTO, TownUserSearchable, TownUserVO> implements TownUserController {

    /** 所站人员 service */
    private final TownUserService townUserService;

    @Override
    protected BaseService<TownUser> getBaseService() {
    return townUserService;
    }

}