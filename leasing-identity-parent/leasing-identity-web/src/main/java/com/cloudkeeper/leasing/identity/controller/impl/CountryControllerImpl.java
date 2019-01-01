package com.cloudkeeper.leasing.identity.controller.impl;

import com.cloudkeeper.leasing.base.controller.impl.BaseControllerImpl;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.controller.CountryController;
import com.cloudkeeper.leasing.identity.domain.Country;
import com.cloudkeeper.leasing.identity.domain.JrResource;
import com.cloudkeeper.leasing.identity.dto.country.CountryDTO;
import com.cloudkeeper.leasing.identity.dto.country.CountrySearchable;
import com.cloudkeeper.leasing.identity.service.CountryService;
import com.cloudkeeper.leasing.identity.service.JrResourceService;
import com.cloudkeeper.leasing.identity.vo.CountryVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 村 controller
 * @author wj
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CountryControllerImpl extends BaseControllerImpl<Country, CountryDTO, CountrySearchable, CountryVO> implements CountryController {

    /** 村 service */
    private final CountryService countryService;
    private final JrResourceService jrResourceService;

    @Override
    protected BaseService<Country> getBaseService() {
    return countryService;
    }

    @Override
    public Result<List<CountryVO>> list(CountrySearchable searchable, Sort sort) {
        Result<List<CountryVO>>  countryVOList=super.list(searchable,sort);
        for (CountryVO countryVO:countryVOList.getContent()){
            List<JrResource> jrResourceList= jrResourceService.findByConnectId(countryVO.getId());
            countryVO.setJrResourceList(jrResourceList);
        }
        return countryVOList;
    }

    @Override
    public Result<Page<CountryVO>> page(CountrySearchable searchable, Pageable pageable) {
        Result<Page<CountryVO>> page = super.page(searchable, pageable);
        for (CountryVO countryVO:page.getContent()){
            List<JrResource> jrResourceList= jrResourceService.findByConnectId(countryVO.getId());
            countryVO.setJrResourceList(jrResourceList);
        }
        return page;
    }
}