package com.cloudkeeper.leasing.identity.controller.impl;

import com.cloudkeeper.leasing.base.controller.impl.BaseControllerImpl;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.controller.CodeConfigController;
import com.cloudkeeper.leasing.identity.domain.CodeConfig;
import com.cloudkeeper.leasing.identity.dto.codeconfig.CodeConfigDTO;
import com.cloudkeeper.leasing.identity.dto.codeconfig.CodeConfigSearchable;
import com.cloudkeeper.leasing.identity.service.CodeConfigService;
import com.cloudkeeper.leasing.identity.vo.CodeConfigVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 编码配置 controller
 * @author asher
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CodeConfigControllerImpl extends BaseControllerImpl<CodeConfig, CodeConfigDTO, CodeConfigSearchable, CodeConfigVO> implements CodeConfigController {

    /** 编码配置 service */
    private final CodeConfigService codeConfigService;

    @Override
    protected BaseService<CodeConfig> getBaseService() {
    return codeConfigService;
    }

}