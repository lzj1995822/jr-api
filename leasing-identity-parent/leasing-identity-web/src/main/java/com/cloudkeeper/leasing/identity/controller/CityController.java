package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.controller.BaseController;
import com.cloudkeeper.leasing.identity.dto.city.CityDTO;
import com.cloudkeeper.leasing.identity.dto.city.CitySearchable;
import com.cloudkeeper.leasing.identity.vo.CityVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 市 controller
 * @author wj
 */
@Api(value = "市", tags = "市")
@RequestMapping("/city")
public interface CityController extends BaseController<CityDTO, CitySearchable, CityVO> {

}