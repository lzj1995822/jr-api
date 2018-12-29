package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.controller.BaseController;
import com.cloudkeeper.leasing.identity.dto.town.TownDTO;
import com.cloudkeeper.leasing.identity.dto.town.TownSearchable;
import com.cloudkeeper.leasing.identity.vo.TownVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 镇 controller
 * @author wj
 */
@Api(value = "镇", tags = "镇")
@RequestMapping("/town")
public interface TownController extends BaseController<TownDTO, TownSearchable, TownVO> {

}