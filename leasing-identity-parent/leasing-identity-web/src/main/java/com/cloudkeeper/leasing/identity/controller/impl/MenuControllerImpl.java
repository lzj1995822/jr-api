package com.cloudkeeper.leasing.identity.controller.impl;

import com.cloudkeeper.leasing.base.controller.impl.BaseControllerImpl;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.controller.MenuController;
import com.cloudkeeper.leasing.identity.domain.Menu;
import com.cloudkeeper.leasing.identity.dto.menu.MenuDTO;
import com.cloudkeeper.leasing.identity.dto.menu.MenuSearchable;
import com.cloudkeeper.leasing.identity.service.MenuService;
import com.cloudkeeper.leasing.identity.vo.MenuVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单 controller
 * @author wj
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MenuControllerImpl extends BaseControllerImpl<Menu, MenuDTO, MenuSearchable, MenuVO> implements MenuController {

    /** 菜单 service */
    private final MenuService menuService;

    @Override
    protected BaseService<Menu> getBaseService() {
    return menuService;
    }

}