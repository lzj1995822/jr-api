package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.controller.BaseController;
import com.cloudkeeper.leasing.identity.dto.record.RecordDTO;
import com.cloudkeeper.leasing.identity.dto.record.RecordSearchable;
import com.cloudkeeper.leasing.identity.vo.RecordVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 活动记录 controller
 * @author wj
 */
@Api(value = "活动记录", tags = "活动记录")
@RequestMapping("/record")
public interface RecordController extends BaseController<RecordDTO, RecordSearchable, RecordVO> {

}