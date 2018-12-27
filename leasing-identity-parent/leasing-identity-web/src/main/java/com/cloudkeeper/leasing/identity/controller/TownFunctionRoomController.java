package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.controller.BaseController;
import com.cloudkeeper.leasing.identity.dto.townfunctionroom.TownFunctionRoomDTO;
import com.cloudkeeper.leasing.identity.dto.townfunctionroom.TownFunctionRoomSearchable;
import com.cloudkeeper.leasing.identity.vo.TownFunctionRoomVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 所站功能室建设 controller
 * @author wj
 */
@Api(value = "所站功能室建设", tags = "所站功能室建设")
@RequestMapping("/townFunctionRoom")
public interface TownFunctionRoomController extends BaseController<TownFunctionRoomDTO, TownFunctionRoomSearchable, TownFunctionRoomVO> {

}