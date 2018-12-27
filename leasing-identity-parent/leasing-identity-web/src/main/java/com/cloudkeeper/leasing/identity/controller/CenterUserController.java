package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.controller.BaseController;
import com.cloudkeeper.leasing.identity.dto.centeruser.CenterUserDTO;
import com.cloudkeeper.leasing.identity.dto.centeruser.CenterUserSearchable;
import com.cloudkeeper.leasing.identity.vo.CenterUserVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 分中心人员 controller
 * @author wj
 */
@Api(value = "分中心人员", tags = "分中心人员")
@RequestMapping("/centerUser")
public interface CenterUserController extends BaseController<CenterUserDTO, CenterUserSearchable, CenterUserVO> {

}