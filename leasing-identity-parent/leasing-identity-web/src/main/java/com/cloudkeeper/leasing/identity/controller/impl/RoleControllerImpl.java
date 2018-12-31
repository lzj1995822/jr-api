package com.cloudkeeper.leasing.identity.controller.impl;

import com.cloudkeeper.leasing.base.controller.impl.BaseControllerImpl;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.controller.RoleController;
import com.cloudkeeper.leasing.identity.domain.Role;
import com.cloudkeeper.leasing.identity.dto.role.RoleDTO;
import com.cloudkeeper.leasing.identity.dto.role.RoleSearchable;
import com.cloudkeeper.leasing.identity.service.RoleService;
import com.cloudkeeper.leasing.identity.vo.RoleVO;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * 角色 controller
 * @author jerry
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleControllerImpl extends BaseControllerImpl<Role, RoleDTO, RoleSearchable, RoleVO> implements RoleController {

    /** 角色 service */
    private final RoleService roleService;

    @Override
    protected BaseService<Role> getBaseService() {
        return roleService;
    }

    @Override
    public Result<Boolean> existsCode(@ApiParam(value = "编码", required = true) @PathVariable String code,
                                      @ApiParam(value = "角色id") @PathVariable(required = false) String id) {
        boolean exists = roleService.existsCode(code, id);
        return Result.of(exists);
    }

    @Override
    public Result<Role> findOneEntity(@PathVariable String id) {
        Optional<Role> optional = roleService.findOptionalById(id);
        if (!optional.isPresent()) {
            return Result.ofNotFound();
        }
        return Result.of(optional.get());
    }
}
