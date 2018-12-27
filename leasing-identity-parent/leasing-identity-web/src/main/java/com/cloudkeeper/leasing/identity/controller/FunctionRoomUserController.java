package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.controller.BaseController;
import com.cloudkeeper.leasing.identity.dto.functionroomuser.FunctionRoomUserDTO;
import com.cloudkeeper.leasing.identity.dto.functionroomuser.FunctionRoomUserSearchable;
import com.cloudkeeper.leasing.identity.vo.FunctionRoomUserVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 功能室人员 controller
 * @author wj
 */
@Api(value = "功能室人员", tags = "功能室人员")
@RequestMapping("/functionRoomUser")
public interface FunctionRoomUserController extends BaseController<FunctionRoomUserDTO, FunctionRoomUserSearchable, FunctionRoomUserVO> {

}