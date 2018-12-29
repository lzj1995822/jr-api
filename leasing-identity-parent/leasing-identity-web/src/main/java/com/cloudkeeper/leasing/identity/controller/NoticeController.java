package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.controller.BaseController;
import com.cloudkeeper.leasing.identity.dto.notice.NoticeDTO;
import com.cloudkeeper.leasing.identity.dto.notice.NoticeSearchable;
import com.cloudkeeper.leasing.identity.vo.NoticeVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 通知 controller
 * @author wj
 */
@Api(value = "通知", tags = "通知")
@RequestMapping("/notice")
public interface NoticeController extends BaseController<NoticeDTO, NoticeSearchable, NoticeVO> {

}