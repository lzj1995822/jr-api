package com.cloudkeeper.leasing.identity.controller.impl;

import com.cloudkeeper.leasing.base.controller.impl.BaseControllerImpl;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.controller.OrgCenterController;
import com.cloudkeeper.leasing.identity.domain.JrResource;
import com.cloudkeeper.leasing.identity.domain.OrgCenter;
import com.cloudkeeper.leasing.identity.dto.orgcenter.OrgCenterDTO;
import com.cloudkeeper.leasing.identity.dto.orgcenter.OrgCenterSearchable;
import com.cloudkeeper.leasing.identity.dto.orgroom.OrgRoomSearchable;
import com.cloudkeeper.leasing.identity.service.JrResourceService;
import com.cloudkeeper.leasing.identity.service.OrgCenterService;
import com.cloudkeeper.leasing.identity.vo.OrgCenterVO;
import com.cloudkeeper.leasing.identity.vo.OrgRoomVO;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RestController;

/**
 * 分中心 controller
 * @author wj
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrgCenterControllerImpl extends BaseControllerImpl<OrgCenter, OrgCenterDTO, OrgCenterSearchable, OrgCenterVO> implements OrgCenterController {

    /** 分中心 service */
    private final OrgCenterService orgCenterService;
    /** 分中心 service */
    private final JrResourceService jrResourceService;

    @Override
    protected BaseService<OrgCenter> getBaseService() {
    return orgCenterService;
    }
    
    @Override
	public Result<List<OrgCenterVO>> list(OrgCenterSearchable searchable, Sort sort) {
		Result<List<OrgCenterVO>> OrgRoomVOList= super.list(searchable, sort);
		for(OrgCenterVO orgCenterVO : OrgRoomVOList.getContent()){
			 List<JrResource> jrResourceList = jrResourceService.findByConnectId(orgCenterVO.getId());
			 orgCenterVO.setJrResourceList(jrResourceList);
		}
		return OrgRoomVOList;
	}
}