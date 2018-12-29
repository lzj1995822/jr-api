package com.cloudkeeper.leasing.identity.controller.impl;

import com.cloudkeeper.leasing.base.controller.impl.BaseControllerImpl;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.controller.CountryController;
import com.cloudkeeper.leasing.identity.domain.Country;
import com.cloudkeeper.leasing.identity.dto.country.CountryDTO;
import com.cloudkeeper.leasing.identity.dto.country.CountrySearchable;
import com.cloudkeeper.leasing.identity.service.CountryService;
import com.cloudkeeper.leasing.identity.vo.CountryVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 村 controller
 * @author wj
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CountryControllerImpl extends BaseControllerImpl<Country, CountryDTO, CountrySearchable, CountryVO> implements CountryController {

    /** 村 service */
    private final CountryService countryService;

    @Override
    protected BaseService<Country> getBaseService() {
    return countryService;
    }

}