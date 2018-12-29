package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.controller.BaseController;
import com.cloudkeeper.leasing.identity.dto.orgroom.OrgRoomDTO;
import com.cloudkeeper.leasing.identity.dto.orgroom.OrgRoomSearchable;
import com.cloudkeeper.leasing.identity.vo.OrgRoomVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 功能室 controller
 * @author wj
 */
@Api(value = "功能室", tags = "功能室")
@RequestMapping("/orgRoom")
public interface OrgRoomController extends BaseController<OrgRoomDTO, OrgRoomSearchable, OrgRoomVO> {

}