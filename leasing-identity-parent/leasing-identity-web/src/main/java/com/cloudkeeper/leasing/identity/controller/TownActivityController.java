package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.controller.BaseController;
import com.cloudkeeper.leasing.identity.dto.townactivity.TownActivityDTO;
import com.cloudkeeper.leasing.identity.dto.townactivity.TownActivitySearchable;
import com.cloudkeeper.leasing.identity.vo.TownActivityVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 镇活动 controller
 * @author hf
 */
@Api(value = "镇活动", tags = "镇活动")
@RequestMapping("/townActivity")
public interface TownActivityController extends BaseController<TownActivityDTO, TownActivitySearchable, TownActivityVO> {

}