package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.controller.BaseController;
import com.cloudkeeper.leasing.identity.dto.menu.MenuDTO;
import com.cloudkeeper.leasing.identity.dto.menu.MenuSearchable;
import com.cloudkeeper.leasing.identity.vo.MenuVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 菜单 controller
 * @author wj
 */
@Api(value = "菜单", tags = "菜单")
@RequestMapping("/menu")
public interface MenuController extends BaseController<MenuDTO, MenuSearchable, MenuVO> {

}