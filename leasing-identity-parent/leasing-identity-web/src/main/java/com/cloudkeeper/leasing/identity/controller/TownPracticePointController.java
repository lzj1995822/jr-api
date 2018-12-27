package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.controller.BaseController;
import com.cloudkeeper.leasing.identity.dto.townpracticepoint.TownPracticePointDTO;
import com.cloudkeeper.leasing.identity.dto.townpracticepoint.TownPracticePointSearchable;
import com.cloudkeeper.leasing.identity.vo.TownPracticePointVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 镇文明实践点 controller
 * @author wj
 */
@Api(value = "镇文明实践点", tags = "镇文明实践点")
@RequestMapping("/townPracticePoint")
public interface TownPracticePointController extends BaseController<TownPracticePointDTO, TownPracticePointSearchable, TownPracticePointVO> {

}