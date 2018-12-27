package com.cloudkeeper.leasing.identity.controller.impl;

import com.cloudkeeper.leasing.base.controller.impl.BaseControllerImpl;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.controller.TownFunctionRoomController;
import com.cloudkeeper.leasing.identity.domain.TownFunctionRoom;
import com.cloudkeeper.leasing.identity.dto.townfunctionroom.TownFunctionRoomDTO;
import com.cloudkeeper.leasing.identity.dto.townfunctionroom.TownFunctionRoomSearchable;
import com.cloudkeeper.leasing.identity.service.TownFunctionRoomService;
import com.cloudkeeper.leasing.identity.vo.TownFunctionRoomVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 所站功能室建设 controller
 * @author wj
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TownFunctionRoomControllerImpl extends BaseControllerImpl<TownFunctionRoom, TownFunctionRoomDTO, TownFunctionRoomSearchable, TownFunctionRoomVO> implements TownFunctionRoomController {

    /** 所站功能室建设 service */
    private final TownFunctionRoomService townFunctionRoomService;

    @Override
    protected BaseService<TownFunctionRoom> getBaseService() {
    return townFunctionRoomService;
    }

}