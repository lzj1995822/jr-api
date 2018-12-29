package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.controller.BaseController;
import com.cloudkeeper.leasing.identity.dto.point.PointDTO;
import com.cloudkeeper.leasing.identity.dto.point.PointSearchable;
import com.cloudkeeper.leasing.identity.vo.PointVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 实践点 controller
 * @author wj
 */
@Api(value = "实践点", tags = "实践点")
@RequestMapping("/point")
public interface PointController extends BaseController<PointDTO, PointSearchable, PointVO> {

}