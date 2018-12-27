package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.controller.BaseController;
import com.cloudkeeper.leasing.identity.dto.centerpracticepoint.CenterPracticePointDTO;
import com.cloudkeeper.leasing.identity.dto.centerpracticepoint.CenterPracticePointSearchable;
import com.cloudkeeper.leasing.identity.vo.CenterPracticePointVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 分中心文明实践点 controller
 * @author wj
 */
@Api(value = "分中心文明实践点", tags = "分中心文明实践点")
@RequestMapping("/centerPracticePoint")
public interface CenterPracticePointController extends BaseController<CenterPracticePointDTO, CenterPracticePointSearchable, CenterPracticePointVO> {

}