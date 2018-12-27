package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.controller.BaseController;
import com.cloudkeeper.leasing.identity.dto.center.CenterDTO;
import com.cloudkeeper.leasing.identity.dto.center.CenterSearchable;
import com.cloudkeeper.leasing.identity.vo.CenterVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 分中心 controller
 * @author wj
 */
@Api(value = "分中心", tags = "分中心")
@RequestMapping("/center")
public interface CenterController extends BaseController<CenterDTO, CenterSearchable, CenterVO> {

}