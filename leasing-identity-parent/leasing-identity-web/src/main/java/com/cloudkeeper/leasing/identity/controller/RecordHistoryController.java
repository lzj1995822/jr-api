package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.controller.BaseController;
import com.cloudkeeper.leasing.identity.dto.recordhistory.RecordHistoryDTO;
import com.cloudkeeper.leasing.identity.dto.recordhistory.RecordHistorySearchable;
import com.cloudkeeper.leasing.identity.vo.RecordHistoryVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 活动记录历史 controller
 * @author wj
 */
@Api(value = "活动记录历史", tags = "活动记录历史")
@RequestMapping("/recordHistory")
public interface RecordHistoryController extends BaseController<RecordHistoryDTO, RecordHistorySearchable, RecordHistoryVO> {

}