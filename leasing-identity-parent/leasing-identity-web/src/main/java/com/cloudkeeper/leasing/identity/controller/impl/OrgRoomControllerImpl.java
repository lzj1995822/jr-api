package com.cloudkeeper.leasing.identity.controller.impl;

import com.cloudkeeper.leasing.base.controller.impl.BaseControllerImpl;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.controller.OrgRoomController;
import com.cloudkeeper.leasing.identity.domain.OrgRoom;
import com.cloudkeeper.leasing.identity.dto.orgroom.OrgRoomDTO;
import com.cloudkeeper.leasing.identity.dto.orgroom.OrgRoomSearchable;
import com.cloudkeeper.leasing.identity.service.OrgRoomService;
import com.cloudkeeper.leasing.identity.vo.OrgRoomVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能室 controller
 * @author wj
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrgRoomControllerImpl extends BaseControllerImpl<OrgRoom, OrgRoomDTO, OrgRoomSearchable, OrgRoomVO> implements OrgRoomController {

    /** 功能室 service */
    private final OrgRoomService orgRoomService;

    @Override
    protected BaseService<OrgRoom> getBaseService() {
    return orgRoomService;
    }

}