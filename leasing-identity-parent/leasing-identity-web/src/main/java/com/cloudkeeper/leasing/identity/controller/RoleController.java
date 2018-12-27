package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.controller.BaseController;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.identity.dto.role.RoleDTO;
import com.cloudkeeper.leasing.identity.dto.role.RoleSearchable;
import com.cloudkeeper.leasing.identity.vo.RoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 角色 controller
 * @author jerry
 */
@Api(value = "角色", tags = "角色")
@RequestMapping("/role")
public interface RoleController extends BaseController<RoleDTO, RoleSearchable, RoleVO> {

    @ApiOperation(value = "编码存在校验", notes = "编码存在校验")
    @GetMapping("/exists/{code}code/{id}id")
    Result<Boolean> existsCode(@ApiParam(value = "编码", required = true) @PathVariable String code,
                               @ApiParam(value = "角色id") @PathVariable(required = false) String id);

}
