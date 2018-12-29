package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.controller.BaseController;
import com.cloudkeeper.leasing.identity.dto.orgperson.OrgPersonDTO;
import com.cloudkeeper.leasing.identity.dto.orgperson.OrgPersonSearchable;
import com.cloudkeeper.leasing.identity.vo.OrgPersonVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 组织架构人员 controller
 * @author wj
 */
@Api(value = "组织架构人员", tags = "组织架构人员")
@RequestMapping("/orgPerson")
public interface OrgPersonController extends BaseController<OrgPersonDTO, OrgPersonSearchable, OrgPersonVO> {

}