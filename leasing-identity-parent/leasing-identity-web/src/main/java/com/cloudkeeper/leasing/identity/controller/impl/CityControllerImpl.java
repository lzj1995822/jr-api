package com.cloudkeeper.leasing.identity.controller.impl;

import com.cloudkeeper.leasing.base.controller.impl.BaseControllerImpl;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.controller.CityController;
import com.cloudkeeper.leasing.identity.domain.City;
import com.cloudkeeper.leasing.identity.domain.JrResource;
import com.cloudkeeper.leasing.identity.dto.city.CityDTO;
import com.cloudkeeper.leasing.identity.dto.city.CitySearchable;
import com.cloudkeeper.leasing.identity.service.CityService;
import com.cloudkeeper.leasing.identity.service.JrResourceService;
import com.cloudkeeper.leasing.identity.vo.CityVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 市 controller
 * @author wj
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CityControllerImpl extends BaseControllerImpl<City, CityDTO, CitySearchable, CityVO> implements CityController {

    /** 市 service */
    private final CityService cityService;
    private final JrResourceService jrResourceService;

    @Override
    protected BaseService<City> getBaseService() {
    return cityService;
    }

    @Override
    public Result<List<CityVO>> list(CitySearchable searchable, Sort sort) {
        Result<List<CityVO>>  cityVOList=super.list(searchable,sort);
        for (CityVO cityVO:cityVOList.getContent()){
            List<JrResource> jrResourceList= jrResourceService.findByConnectId(cityVO.getId());
            cityVO.setJrResourceList(jrResourceList);
        }
        return cityVOList;
    }
}