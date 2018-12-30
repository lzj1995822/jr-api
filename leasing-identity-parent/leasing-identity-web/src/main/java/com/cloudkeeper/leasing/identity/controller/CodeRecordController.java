package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.controller.BaseController;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.identity.dto.coderecord.CodeRecordDTO;
import com.cloudkeeper.leasing.identity.dto.coderecord.CodeRecordSearchable;
import com.cloudkeeper.leasing.identity.vo.CodeRecordVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 编码生成记录 controller
 * @author asher
 */
@RequestMapping("/codeRecord")
public interface CodeRecordController extends BaseController<CodeRecordDTO, CodeRecordSearchable, CodeRecordVO> {

    @ApiOperation(value = "生成编码", notes = "生成编码", position = 7)
    @GetMapping("/{configId}configId")
    Result<String> getCode(@PathVariable String configId);

}