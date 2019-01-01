package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.controller.BaseController;
import com.cloudkeeper.leasing.identity.dto.recordspecial.RecordSpecialDTO;
import com.cloudkeeper.leasing.identity.dto.recordspecial.RecordSpecialSearchable;
import com.cloudkeeper.leasing.identity.vo.RecordSpecialVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 特殊活动 controller
 * @author hf
 */
@Api(value = "特殊活动", tags = "特殊活动")
@RequestMapping("/recordSpecial")
public interface RecordSpecialController extends BaseController<RecordSpecialDTO, RecordSpecialSearchable, RecordSpecialVO> {

}