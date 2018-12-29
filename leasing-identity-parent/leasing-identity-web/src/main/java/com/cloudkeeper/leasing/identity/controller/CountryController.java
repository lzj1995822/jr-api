package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.controller.BaseController;
import com.cloudkeeper.leasing.identity.dto.country.CountryDTO;
import com.cloudkeeper.leasing.identity.dto.country.CountrySearchable;
import com.cloudkeeper.leasing.identity.vo.CountryVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 村 controller
 * @author wj
 */
@Api(value = "村", tags = "村")
@RequestMapping("/country")
public interface CountryController extends BaseController<CountryDTO, CountrySearchable, CountryVO> {

}