package com.cloudkeeper.leasing.identity.controller.impl;

import com.cloudkeeper.leasing.base.controller.impl.BaseControllerImpl;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.controller.OrgRoomController;
import com.cloudkeeper.leasing.identity.domain.JrResource;
import com.cloudkeeper.leasing.identity.domain.OrgRoom;
import com.cloudkeeper.leasing.identity.dto.country.CountrySearchable;
import com.cloudkeeper.leasing.identity.dto.orgroom.OrgRoomDTO;
import com.cloudkeeper.leasing.identity.dto.orgroom.OrgRoomSearchable;
import com.cloudkeeper.leasing.identity.service.JrResourceService;
import com.cloudkeeper.leasing.identity.service.OrgRoomService;
import com.cloudkeeper.leasing.identity.vo.CountryVO;
import com.cloudkeeper.leasing.identity.vo.OrgRoomVO;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    private final JrResourceService jrResourceService;

    @Override
    protected BaseService<OrgRoom> getBaseService() {
    return orgRoomService;
    }
    
	@Override
	public Result<List<OrgRoomVO>> list(OrgRoomSearchable searchable, Sort sort) {
		Result<List<OrgRoomVO>> OrgRoomVOList= super.list(searchable, sort);
		for(OrgRoomVO orgRoomVO : OrgRoomVOList.getContent()){
			 List<JrResource> jrResourceList = jrResourceService.findByConnectId(orgRoomVO.getId());
			 orgRoomVO.setJrResourceList(jrResourceList);
		}
		return OrgRoomVOList;
	}
  
}