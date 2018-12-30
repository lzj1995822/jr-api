package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.controller.BaseController;
import com.cloudkeeper.leasing.identity.dto.orgcenter.OrgCenterDTO;
import com.cloudkeeper.leasing.identity.dto.orgcenter.OrgCenterSearchable;
import com.cloudkeeper.leasing.identity.vo.OrgCenterVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 分中心 controller
 * @author wj
 */
@Api(value = "分中心组织", tags = "分中心组织")
@RequestMapping("/orgCenter")
public interface OrgCenterController extends BaseController<OrgCenterDTO, OrgCenterSearchable, OrgCenterVO> {

}