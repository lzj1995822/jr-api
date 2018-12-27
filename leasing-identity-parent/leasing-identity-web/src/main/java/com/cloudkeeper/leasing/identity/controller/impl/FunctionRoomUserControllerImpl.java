package com.cloudkeeper.leasing.identity.controller.impl;

import com.cloudkeeper.leasing.base.controller.impl.BaseControllerImpl;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.controller.FunctionRoomUserController;
import com.cloudkeeper.leasing.identity.domain.FunctionRoomUser;
import com.cloudkeeper.leasing.identity.dto.functionroomuser.FunctionRoomUserDTO;
import com.cloudkeeper.leasing.identity.dto.functionroomuser.FunctionRoomUserSearchable;
import com.cloudkeeper.leasing.identity.service.FunctionRoomUserService;
import com.cloudkeeper.leasing.identity.vo.FunctionRoomUserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能室人员 controller
 * @author wj
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FunctionRoomUserControllerImpl extends BaseControllerImpl<FunctionRoomUser, FunctionRoomUserDTO, FunctionRoomUserSearchable, FunctionRoomUserVO> implements FunctionRoomUserController {

    /** 功能室人员 service */
    private final FunctionRoomUserService functionRoomUserService;

    @Override
    protected BaseService<FunctionRoomUser> getBaseService() {
    return functionRoomUserService;
    }

}