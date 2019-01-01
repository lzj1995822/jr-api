package com.cloudkeeper.leasing.identity.controller.impl;

import com.cloudkeeper.leasing.base.controller.impl.BaseControllerImpl;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.controller.OrgPersonController;
import com.cloudkeeper.leasing.identity.domain.OrgPerson;
import com.cloudkeeper.leasing.identity.dto.orgperson.OrgPersonDTO;
import com.cloudkeeper.leasing.identity.dto.orgperson.OrgPersonSearchable;
import com.cloudkeeper.leasing.identity.service.OrgPersonService;
import com.cloudkeeper.leasing.identity.vo.OrgPersonVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 组织架构人员 controller
 * @author wj
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrgPersonControllerImpl extends BaseControllerImpl<OrgPerson, OrgPersonDTO, OrgPersonSearchable, OrgPersonVO> implements OrgPersonController {

    /** 组织架构人员 service */
    private final OrgPersonService orgPersonService;

    @Override
    protected BaseService<OrgPerson> getBaseService() {
    return orgPersonService;
    }

    @Override
    public Result<List<OrgPersonVO>> list(OrgPersonSearchable searchable, Sort sort) {
        List<OrgPerson> all = orgPersonService.findAll(searchable, sort);
        return super.list(searchable, sort);
    }
}