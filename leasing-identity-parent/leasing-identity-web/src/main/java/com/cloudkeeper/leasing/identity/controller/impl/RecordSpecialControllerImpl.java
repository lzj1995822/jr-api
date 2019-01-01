package com.cloudkeeper.leasing.identity.controller.impl;

import com.cloudkeeper.leasing.base.controller.impl.BaseControllerImpl;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.controller.RecordSpecialController;
import com.cloudkeeper.leasing.identity.domain.RecordSpecial;
import com.cloudkeeper.leasing.identity.dto.recordspecial.RecordSpecialDTO;
import com.cloudkeeper.leasing.identity.dto.recordspecial.RecordSpecialSearchable;
import com.cloudkeeper.leasing.identity.service.RecordSpecialService;
import com.cloudkeeper.leasing.identity.vo.RecordSpecialVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 特殊活动 controller
 * @author hf
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecordSpecialControllerImpl extends BaseControllerImpl<RecordSpecial, RecordSpecialDTO, RecordSpecialSearchable, RecordSpecialVO> implements RecordSpecialController {

    /** 特殊活动 service */
    private final RecordSpecialService recordSpecialService;

    @Override
    protected BaseService<RecordSpecial> getBaseService() {
    return recordSpecialService;
    }

}