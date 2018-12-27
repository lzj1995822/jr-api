package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.controller.BaseController;
import com.cloudkeeper.leasing.identity.dto.townuser.TownUserDTO;
import com.cloudkeeper.leasing.identity.dto.townuser.TownUserSearchable;
import com.cloudkeeper.leasing.identity.vo.TownUserVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 所站人员 controller
 * @author wj
 */
@Api(value = "所站人员", tags = "所站人员")
@RequestMapping("/townUser")
public interface TownUserController extends BaseController<TownUserDTO, TownUserSearchable, TownUserVO> {

}