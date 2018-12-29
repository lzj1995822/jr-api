package com.cloudkeeper.leasing.identity.controller.impl;

import com.cloudkeeper.leasing.base.controller.impl.BaseControllerImpl;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.controller.CityController;
import com.cloudkeeper.leasing.identity.domain.City;
import com.cloudkeeper.leasing.identity.dto.city.CityDTO;
import com.cloudkeeper.leasing.identity.dto.city.CitySearchable;
import com.cloudkeeper.leasing.identity.service.CityService;
import com.cloudkeeper.leasing.identity.vo.CityVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 市 controller
 * @author wj
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CityControllerImpl extends BaseControllerImpl<City, CityDTO, CitySearchable, CityVO> implements CityController {

    /** 市 service */
    private final CityService cityService;

    @Override
    protected BaseService<City> getBaseService() {
    return cityService;
    }

}