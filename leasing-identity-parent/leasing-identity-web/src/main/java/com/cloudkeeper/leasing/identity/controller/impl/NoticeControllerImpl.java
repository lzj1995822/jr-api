package com.cloudkeeper.leasing.identity.controller.impl;

import com.cloudkeeper.leasing.base.controller.impl.BaseControllerImpl;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.controller.NoticeController;
import com.cloudkeeper.leasing.identity.domain.Notice;
import com.cloudkeeper.leasing.identity.dto.notice.NoticeDTO;
import com.cloudkeeper.leasing.identity.dto.notice.NoticeSearchable;
import com.cloudkeeper.leasing.identity.service.NoticeService;
import com.cloudkeeper.leasing.identity.vo.NoticeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 通知 controller
 * @author wj
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NoticeControllerImpl extends BaseControllerImpl<Notice, NoticeDTO, NoticeSearchable, NoticeVO> implements NoticeController {

    /** 通知 service */
    private final NoticeService noticeService;

    @Override
    protected BaseService<Notice> getBaseService() {
    return noticeService;
    }

}