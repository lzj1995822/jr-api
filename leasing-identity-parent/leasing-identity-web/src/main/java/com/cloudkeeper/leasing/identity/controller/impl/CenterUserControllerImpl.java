package com.cloudkeeper.leasing.identity.controller.impl;

import com.cloudkeeper.leasing.base.controller.impl.BaseControllerImpl;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.controller.CenterUserController;
import com.cloudkeeper.leasing.identity.domain.CenterUser;
import com.cloudkeeper.leasing.identity.dto.centeruser.CenterUserDTO;
import com.cloudkeeper.leasing.identity.dto.centeruser.CenterUserSearchable;
import com.cloudkeeper.leasing.identity.service.CenterUserService;
import com.cloudkeeper.leasing.identity.vo.CenterUserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 分中心人员 controller
 * @author wj
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CenterUserControllerImpl extends BaseControllerImpl<CenterUser, CenterUserDTO, CenterUserSearchable, CenterUserVO> implements CenterUserController {

    /** 分中心人员 service */
    private final CenterUserService centerUserService;

    @Override
    protected BaseService<CenterUser> getBaseService() {
    return centerUserService;
    }

}