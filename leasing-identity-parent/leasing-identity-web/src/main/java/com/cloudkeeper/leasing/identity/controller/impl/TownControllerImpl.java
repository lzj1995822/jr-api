package com.cloudkeeper.leasing.identity.controller.impl;

import com.cloudkeeper.leasing.base.controller.impl.BaseControllerImpl;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.controller.TownController;
import com.cloudkeeper.leasing.identity.domain.JrResource;
import com.cloudkeeper.leasing.identity.domain.Town;
import com.cloudkeeper.leasing.identity.dto.town.TownDTO;
import com.cloudkeeper.leasing.identity.dto.town.TownSearchable;
import com.cloudkeeper.leasing.identity.service.JrResourceService;
import com.cloudkeeper.leasing.identity.service.TownService;
import com.cloudkeeper.leasing.identity.vo.TownVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 镇 controller
 * @author wj
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TownControllerImpl extends BaseControllerImpl<Town, TownDTO, TownSearchable, TownVO> implements TownController {

    /** 镇 service */
    private final TownService townService;
    private final JrResourceService jrResourceService;


    @Override
    protected BaseService<Town> getBaseService() {
    return townService;
    }

    @Override
    public Result<List<TownVO>> list(TownSearchable searchable, Sort sort) {
        Result<List<TownVO>> townlist=super.list(searchable,sort);
        for (TownVO townVO:townlist.getContent()){
            List<JrResource> jrResourceList= jrResourceService.findByConnectId(townVO.getId());
            townVO.setJrResourceList(jrResourceList);
        }

        return townlist;
    }
}