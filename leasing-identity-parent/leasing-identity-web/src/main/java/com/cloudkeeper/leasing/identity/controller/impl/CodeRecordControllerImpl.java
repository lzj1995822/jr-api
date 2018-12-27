package com.cloudkeeper.leasing.identity.controller.impl;

import com.cloudkeeper.leasing.base.controller.impl.BaseControllerImpl;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.controller.CodeRecordController;
import com.cloudkeeper.leasing.identity.domain.CodeRecord;
import com.cloudkeeper.leasing.identity.dto.coderecord.CodeRecordDTO;
import com.cloudkeeper.leasing.identity.dto.coderecord.CodeRecordSearchable;
import com.cloudkeeper.leasing.identity.service.CodeRecordService;
import com.cloudkeeper.leasing.identity.vo.CodeRecordVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 编码生成记录 controller
 * @author asher
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CodeRecordControllerImpl extends BaseControllerImpl<CodeRecord, CodeRecordDTO, CodeRecordSearchable, CodeRecordVO> implements CodeRecordController {

    /** 编码生成记录 service */
    private final CodeRecordService codeRecordService;

    @Override
    protected BaseService<CodeRecord> getBaseService() {
    return codeRecordService;
    }

    @Override
    public Result<String> getCode(String configId) {
        String code = codeRecordService.getCode(configId);
        if (code == null) {
            return Result.ofNotFound();
        }
        return Result.of(code);
    }
}