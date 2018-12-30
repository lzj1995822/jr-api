package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.controller.BaseController;
import com.cloudkeeper.leasing.identity.dto.codeconfig.CodeConfigDTO;
import com.cloudkeeper.leasing.identity.dto.codeconfig.CodeConfigSearchable;
import com.cloudkeeper.leasing.identity.vo.CodeConfigVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 编码配置 controller
 * @author asher
 */
@RequestMapping("/codeConfig")
public interface CodeConfigController extends BaseController<CodeConfigDTO, CodeConfigSearchable, CodeConfigVO> {

}