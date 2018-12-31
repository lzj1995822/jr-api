package com.cloudkeeper.leasing.identity.controller.impl;

import com.cloudkeeper.leasing.base.controller.impl.BaseControllerImpl;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.controller.NoticeController;
import com.cloudkeeper.leasing.identity.domain.Notice;
import com.cloudkeeper.leasing.identity.dto.notice.NoticeDTO;
import com.cloudkeeper.leasing.identity.dto.notice.NoticeSearchable;
import com.cloudkeeper.leasing.identity.service.NoticeService;
import com.cloudkeeper.leasing.identity.vo.NoticeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Override
    public Result<Page<NoticeVO>> page(NoticeSearchable searchable, Pageable pageable) {
        Page<Notice> all = noticeService.findAll(searchable, pageable);
        List<NoticeVO> noticeVOS = noticeService.handleFile(all.getContent());
        Page<NoticeVO> page = new PageImpl<>(noticeVOS, pageable, all.getTotalElements());
        return Result.of(page);
    }
}